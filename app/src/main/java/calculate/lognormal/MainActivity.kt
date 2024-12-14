package calculate.lognormal

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import calculate.lognormal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val muView = binding.meanVal
        val sigmaView = binding.varianceValue
        val button = binding.getRandomNum
        val result = binding.randomNumberResult

        button.setOnClickListener {
            viewModel.calcLogNormal(muView.text.toString(), sigmaView.text.toString())
        }

        viewModel.result.observe(this) {
            result.text = it
        }
    }
}
