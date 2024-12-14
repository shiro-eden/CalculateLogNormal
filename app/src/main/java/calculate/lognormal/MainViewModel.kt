package calculate.lognormal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.apache.commons.math3.distribution.LogNormalDistribution
import java.security.InvalidParameterException

class MainViewModel: ViewModel() {
    private val resultField = MutableLiveData<String>()
    val result: LiveData<String> = resultField

    fun calcLogNormal(muString: String, sigmaString: String) {
        try {
            val (mu, sigma) = validateParameters(muString, sigmaString)
            val res = LogNormalDistribution(mu, sigma).sample()
            resultField.postValue(res.toString())
        } catch (e: InvalidParameterException) {
            resultField.postValue(e.message)
        }
    }

    private fun checkEmpty(muString: String, sigmaString: String) {
        if (muString.isEmpty() || sigmaString.isEmpty())
            throw InvalidParameterException("Заполните все поля")
    }

    private fun validateParameters(muString: String, sigmaString: String): Pair<Double, Double> {
        checkEmpty(muString, sigmaString)
        val mu = muString.toDoubleOrNull()
        val sigma = sigmaString.toDoubleOrNull()
        when {
            mu == null || sigma == null -> throw InvalidParameterException("Введенные значения в полях слишком маленькие/большие")
            sigma == 0.0 -> throw InvalidParameterException("Параметр σ²>0")
        }
        return Pair(mu!!, sigma!!)
    }
}