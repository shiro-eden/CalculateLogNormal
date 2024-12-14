package calculate.lognormal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.security.InvalidParameterException

class MainViewModel: ViewModel() {
    private val resultField = MutableLiveData<String>()
    val result: LiveData<String> = resultField

    fun calcLogNormal(muString: String, sigmaString: String) {
        try {
            val (mu, sigma) = Utils.validateParameters(muString, sigmaString)
            val res = Utils.logNormalNotInfinity(mu, sigma)
            resultField.postValue(res.toString())
        } catch (e: InvalidParameterException) {
            resultField.postValue(e.message)
        }
    }
}
