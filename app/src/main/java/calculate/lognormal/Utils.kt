package calculate.lognormal

import org.apache.commons.math3.distribution.LogNormalDistribution
import java.security.InvalidParameterException

object Utils {
    private fun checkEmpty(muString: String, sigmaString: String) {
        if (muString.isEmpty() || sigmaString.isEmpty())
            throw InvalidParameterException("Заполните все поля")
    }

    fun validateParameters(muString: String, sigmaString: String): Pair<Double, Double> {
        checkEmpty(muString, sigmaString)
        val mu = muString.toDoubleOrNull()
        val sigma = sigmaString.toDoubleOrNull()
        when {
            mu == null || sigma == null ->
                throw InvalidParameterException("Введенные значения в полях слишком маленькие/большие")
            sigma == 0.0 -> throw InvalidParameterException("Параметр σ²>0")
        }
        return Pair(mu!!, sigma!!)
    }

    fun logNormalDistribution(mu: Double, sigma: Double): Double {
        return LogNormalDistribution(mu, sigma).sample()
    }
}
