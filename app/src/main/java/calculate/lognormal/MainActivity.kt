package calculate.lognormal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import calculate.lognormal.databinding.ActivityMainBinding

import org.apache.commons.math3.distribution.LogNormalDistribution


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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

    private fun validateParameters(muString: String, sigmaString: String): Pair<Double, Double> {
        if (muString.isEmpty() || sigmaString.isEmpty()) throw Exception("Fill in all fields")
        val mu = muString.toDoubleOrNull()
        val sigma = sigmaString.toDoubleOrNull()
        when {
            mu == null || sigma == null -> throw Exception("Variables are not in Double")
            sigma == 0.0 -> throw Exception("Sigma has to be more than 0")
        }
        return Pair(mu!!, sigma!!)
    }
}