package com.example.quizapp.views
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMainBinding
import com.example.quizapp.databinding.DialogBoxBinding
import com.example.quizapp.viewModel.MainViewModel
import com.example.quizapp.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContentView(mainBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainViewModel= ViewModelProvider(this,MainViewModelFactory())[MainViewModel::class.java]
            mainBinding.progressBar.setMax(mainViewModel.questionCount)

        mainBinding.apply {
            option1.setOnClickListener(this@MainActivity)
            option2.setOnClickListener(this@MainActivity)
            option3.setOnClickListener(this@MainActivity)
            option4.setOnClickListener(this@MainActivity)
            next.setOnClickListener(this@MainActivity)
        }
        mainBinding.apply {
            mainViewModel.quizList.get(0).images?.let { quizImage.setImageResource(it) }
            quizQuestions.text=mainViewModel.quizList.get(0).questions
            option1.text=mainViewModel.quizList.get(0).options.get(0)
            option2.text=mainViewModel.quizList.get(0).options.get(1)
            option3.text=mainViewModel.quizList.get(0).options.get(2)
            option4.text=mainViewModel.quizList.get(0).options.get(3)
            mainBinding.questionCounter.text="Questions(${1}/10)"
            mainBinding.progressBar.setProgress(1)
        }
        startAnim()
    }
    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        mainBinding.apply {
            option1.setBackgroundColor(this@MainActivity.getColor(R.color.green))
            option2.setBackgroundColor(this@MainActivity.getColor(R.color.green))
            option3.setBackgroundColor(this@MainActivity.getColor(R.color.green))
            option4.setBackgroundColor(this@MainActivity.getColor(R.color.green))
        }
        var selectedAnswer=""
        val currentBtn=v as AppCompatButton
        if(v.id==R.id.next){
            mainViewModel.counter++
            if(mainViewModel.counter<mainViewModel.quizList.size){
            mainBinding.questionCounter.text = "Questions(${mainViewModel.counter + 1}/10)"
            mainBinding.progressBar.setProgress(mainViewModel.counter + 1)
            startAnim()
            nextQuestion(mainViewModel.counter)
            }
           else{
               showCustomDialog()
            }
        }
        else{
            selectedAnswer=currentBtn.text.toString()
            if(mainViewModel.counter<mainViewModel.quizList.size) {
                if (selectedAnswer == mainViewModel.quizList.get(mainViewModel.counter).answer) {
                    mainViewModel.score++
                    mainBinding.questionCounter.text = "Questions(${mainViewModel.counter + 1}/10)"
                    mainBinding.progressBar.setProgress(mainViewModel.counter + 1)
                } else {
                    mainBinding.questionCounter.text = "Questions(${mainViewModel.counter + 1}/10)"
                    mainBinding.progressBar.setProgress(mainViewModel.counter + 1)
                }
            }
            currentBtn.setBackgroundColor(getColor(R.color.blue))
        }
    }
    private fun startAnim(){
        val slideIn = AnimationUtils.loadAnimation(this, R.anim.right_trasations)
        mainBinding.apply {
            quizQuestions.startAnimation(slideIn)
            option1.startAnimation(slideIn)
            option2.startAnimation(slideIn)
            option3.startAnimation(slideIn)
            option4.startAnimation(slideIn)
            quizImage.startAnimation(slideIn)
        }
    }
    private fun nextQuestion(index:Int){
        mainBinding.apply {
            mainViewModel.quizList.get(index).images?.let { quizImage.setImageResource(it) }
            quizQuestions.text=mainViewModel.quizList.get(index).questions
            option1.text=mainViewModel.quizList.get(index).options.get(0)
            option2.text=mainViewModel.quizList.get(index).options.get(1)
            option3.text=mainViewModel.quizList.get(index).options.get(2)
            option4.text=mainViewModel.quizList.get(index).options.get(3)
        }
    }
    private fun showCustomDialog() {
        val binding=DialogBoxBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(this)
        builder.setView(binding.root)
        val dialog: AlertDialog = builder.create()
        if(mainViewModel.score>=4)
        {
            binding.displayResult.text="Congrats! You have passed the quiz"
            binding.outOfResult.text="${mainViewModel.score} Out of 10 are corrected"
        }
        else{
            binding.displayResult.text="Sorry! failed to pass the quiz"
            binding.outOfResult.text="${mainViewModel.score} Out of 10 are corrected"
        }
        binding.finish.setOnClickListener {
            dialog.dismiss()
            mainViewModel.counter=0
            mainViewModel.score=0
            if(mainViewModel.counter==0){
                mainBinding.apply {
                    mainViewModel.quizList.get(0).images?.let { quizImage.setImageResource(it) }
                    quizQuestions.text=mainViewModel.quizList.get(0).questions
                    option1.text=mainViewModel.quizList.get(0).options.get(0)
                    option2.text=mainViewModel.quizList.get(0).options.get(1)
                    option3.text=mainViewModel.quizList.get(0).options.get(2)
                    option4.text=mainViewModel.quizList.get(0).options.get(3)
                    mainBinding.questionCounter.text="Questions(${1}/10)"
                    mainBinding.progressBar.setProgress(1)
                }
                startAnim()
            }
        }
        // Show the dialog
        dialog.show()
    }
}