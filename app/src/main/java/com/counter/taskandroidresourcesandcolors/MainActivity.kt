package com.counter.taskandroidresourcesandcolors

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.counter.taskandroidresourcesandcolors.ui.theme.TaskAndroidResourcesAndColorsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskAndroidResourcesAndColorsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainApp()
                }
            }
        }
    }
}

@Composable
fun MainApp() {
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween) {

        Game()
    }

}

@Composable
fun Game() {
    val questions = listOf<String>("Do birds fly?", "Is the sea blue?", "Is the sky cloudy?")
    val answers = listOf<Boolean>(true, true, false)
    var count = remember {
        mutableStateOf(0)
    }
    var answer = remember {
        mutableStateOf(false)
    }
    var currentQuestionIndex = remember {
        mutableStateOf(0)
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxSize()
            .padding(15.dp)
    ) {
        Text(
            text = questions[currentQuestionIndex.value],
            fontSize = 20.sp
        )
        if(answer.value && count.value % 2 == 1){
            Text(text ="Correct Answer",
                fontSize = 20.sp)

        }else if (!answer.value && count.value % 2 == 1){
            Text(text ="Incorrect Answer",
                fontSize = 20.sp)
        }
        if(count.value % 2 == 1) {
            Button(
                onClick = {
                    currentQuestionIndex.value++
                    count.value++
                    if(currentQuestionIndex.value == questions.size){
                        count.value = 0
                        currentQuestionIndex.value = 0
                    }
                },
                modifier = Modifier.width(350.dp)
            ) {
                Text(
                    text = "Next Question",
                    fontSize = 20.sp
                )

            }
        } else {
            Row {
                Button(
                    onClick = {
                        if (answers[currentQuestionIndex.value]) {
                            answer.value = true
                            count.value++
                        } else {
                            answer.value = false
                            count.value++
                        }
                    },
                    modifier = Modifier.width(150.dp)

                ) {
                    Text(
                        "True",
                        fontSize = 20.sp
                    )

                }

                Button(
                    onClick = {
                        if (!answers[currentQuestionIndex.value]) {
                            answer.value = true
                            count.value++
                        } else {
                            answer.value = false
                            count.value++
                        }
                    },
                    modifier = Modifier.width(150.dp)

                ) {
                    Text(
                        "False",
                        fontSize = 20.sp
                    )

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskAndroidResourcesAndColorsTheme {
        MainApp()
    }
}