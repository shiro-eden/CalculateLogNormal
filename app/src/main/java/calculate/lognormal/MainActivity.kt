package calculate.lognormal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import calculate.lognormal.databinding.ActivityMainBinding

import org.apache.commons.math3.distribution.LogNormalDistribution


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val resultKey = "ResultKey"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val muView = binding.meanVal
        val sigmaView = binding.varianceValue
        val button = binding.getRandomNum
        val result = binding.randomNumberResult

        button.setOnClickListener {
            try {
                val (mu, sigma) = validateParameters(muView.text.toString(), sigmaView.text.toString())
                val res = LogNormalDistribution(mu, sigma).sample()

                result.text = res.toString()
            } catch (e: Exception) {
                result.text = e.message
                return@setOnClickListener
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putString(resultKey, binding.randomNumberResult.text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedInstanceState.getString(resultKey)?.let {
            binding.randomNumberResult.text = it
        }
    }

    private fun validateParameters(muString: String, sigmaString: String): Pair<Double, Double> {
        if (muString.isEmpty() || sigmaString.isEmpty()) throw Exception(getString(R.string.error_empty_fields))
        val mu = muString.toDoubleOrNull()
        val sigma = sigmaString.toDoubleOrNull()
        when {
            mu == null || sigma == null -> throw Exception(getString(R.string.error_double_max_or_min))
            sigma == 0.0 -> throw Exception(getString(R.string.error_sigma_equal_zero))
        }
        return Pair(mu!!, sigma!!)
    }
}