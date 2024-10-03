package sheridan.dieptu.navigationbasic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import sheridan.dieptu.navigationbasic.ui.theme.NavigationBasicTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationBasicTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    RadioButtonLayout(
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    MainPage(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


//default function code
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
//Create a navigation stack and route
fun MainPage(modifier: Modifier = Modifier){
    //please go to : https://developer.android.com/develop/ui/compose/navigation
    //and add some dependencies in Gradle script -> module: app before doing the navigation

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "radio_button_layout") {
        composable("radio_button_layout") {
            RadioButtonLayout(navController)
        }
        // Define the route to pass an argument
        composable(
            //the route of the navigation
            route = "second_screen/{selection}",
            //the argument that you want to pass when you navigate
            arguments = listOf(navArgument("selection") { defaultValue = "True" })
        ) { backStackEntry ->
            val selection = backStackEntry.arguments?.getString("selection")
            SecondScreen(selection)
        }
    }


}



//layout radiobutton to select and send data to another page
@Composable
fun RadioButtonLayout(navController: NavHostController){
    //var because the value of the variable will change when the user select or enter someting new
    // by is property delegation to delegate the behaviour of boolvalue to remember{...}
    // remember is a function to remember/retain the state of the variable when the user change the phone orientation or move the app to the background
    //mutableStateOf = the state of the variable will change
    var boolValue by remember { mutableStateOf(true) }
    //val navController = rememberNavController()

    Column (modifier = Modifier.padding(16.dp)){
        Text(text = "Select an option: ")

        Row(){
            RadioButton(
                selected = boolValue == true, //select when the mutableStateOf boolValue == true
                onClick = {
                    boolValue = true
                    }
            )
            Text(text = "True Button")

        }

        Row(){
            RadioButton(
                selected = boolValue == false, //select when the mutableStateOf boolValue == false
                onClick = {
                    boolValue = false
                }
            )
            Text(text = "False Button")

        }
        Button(onClick = {
            navController.navigate("second_screen/$boolValue")
        }){
            Text(text = "Go to the next page")

        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NavigationBasicTheme {
        //Greeting("Android")
        //RadioButtonLayout()
    }
}