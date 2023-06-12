package com.example.whatsappclone
import android.os.Bundle
import android.util.LayoutDirection
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposableInferredTarget
import androidx.compose.runtime.InternalComposeApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.whatsappclone.ui.theme.WhatsappCloneTheme
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhatsappCloneTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    Navigation()
                }
            }
        }
    }
}
class TriangleEdgeShape(val offset: Int) : Shape {

    override fun createOutline(
        size: androidx.compose.ui.geometry.Size,
        layoutDirection: androidx.compose.ui.unit.LayoutDirection,
        density: Density
    ): Outline {
        val trianglePath = Path().apply {
            moveTo(x = 0f, y = size.height-offset.toFloat())
            lineTo(x = 0f, y = size.height.toFloat())
            lineTo(x = 0f + offset, y = size.height.toFloat())
        }
        return Outline.Generic(path = trianglePath)
    }
}
@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainUI(navController = navController)
        }
        composable(route = Screen.DetailScreen.route + "/{name}/{quote}", arguments = listOf(
            navArgument("name"){
                type = NavType.StringType
                defaultValue = "Hamza"
                nullable = true
            },
            navArgument("quote"){
                type = NavType.StringType
                defaultValue = "Quote"
                nullable = true
            }
        )){entry ->
            DetailScreen(navController = navController ,name = entry.arguments?.getString( "name"),quote = entry.arguments?.getString("quote"))

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController,name : String?,quote : String?) {
    Column(Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.cat),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(6.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = "$name")
                }
            },
            navigationIcon = {
                IconButton(onClick = { navController.navigate(Screen.MainScreen.route) }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            },
            actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(painter = painterResource(id = R.drawable.clipart2144865), modifier = Modifier
                        .size(25.dp), contentDescription = null)
                }
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Call, null)
                }
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(painter = painterResource(id = R.drawable.noun_three_dots_2854151), modifier = Modifier
                            .size(25.dp), contentDescription = null)
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF006257), navigationIconContentColor = Color.White, titleContentColor = Color.White, actionIconContentColor = Color.White)
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(0xFFEBE5DE))
                .padding(start = 15.dp, end = 15.dp, bottom = 5.dp), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Bottom) {
            val cornerRadius = 16.dp
            val gradientColor = listOf(Color.White, Color.White)
            GradientButton(
                gradientColors = gradientColor,
                cornerRadius = cornerRadius,
                nameButton = "$quote",
                roundedCornerShape = RoundedCornerShape(bottomStart = 30.dp)
            )
//            GradientButton(
//                gradientColors = listOf((Color(0xFF006257)), Color(0xFF006257)),
//                cornerRadius = 16.dp,
//                nameButton = "current_msg",
//                roundedCornerShape =RoundedCornerShape(bottomEnd = 30.dp)
//            )
            new_msg()
        }
    }
}

//@OptIn(InternalComposeApi::class)
//@ComposableInferredTarget("")
//fun test(name: String?){
//    LazyColumn {
//                                items(names) { currentmsg ->
//                                    Text(text = currentmsg)
//                                }
//                            }
//}

////@Composable
//fun new_texts(name: String?) {
//    test(name = name)
//    if name.isNotBlank() {
//        names = names + name
//    }
//    LazyColumn {
//        items(names) {current_msg ->
//            GradientButton(
//                gradientColors = listOf(Color.White, Color.White),
//                cornerRadius = 16.dp,
//                nameButton = current_msg,
//                roundedCornerShape =RoundedCornerShape(bottomEnd = 30.dp)
//            )
//
//        }
//    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun new_msg() {
    var value by remember {
        mutableStateOf("")
    }
    var names by remember { mutableStateOf(listOf<String>()) }
    Row {
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 20.dp)
                .weight(1f)
                ,
            value = value,
            onValueChange = { newText ->
                value = newText
            },
            shape = CircleShape,
            colors = TextFieldDefaults.textFieldColors(containerColor = Color.White, textColor = Color.Black,focusedIndicatorColor = Color.Transparent, // Remove focus indicator color
                unfocusedIndicatorColor = Color.Transparent),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Face,
                    contentDescription = "",
//                    modifier = Modifier.clickable(onClick = { searchbox.value = false }),
                    tint = Color.Gray
                )
            },
            trailingIcon = {
                Row {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Person Icon", tint = Color.Gray, modifier = Modifier.clickable(onClick = {
                            names = names + value
//                            new_texts(name = value)
//                            LazyColumn {
//                                items(names) { currentmsg ->
//                                    Text(text = currentmsg)
//                                }
//                            }
                        }),
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_camera_alt_24),
                        contentDescription = "Person Icon", tint = Color.Gray, modifier = Modifier.padding(start = 20.dp, end = 12.dp)
                    )
                }
            },
            placeholder = { Text(text = "Message", color = Color.Gray) }
        )
        Box(modifier = Modifier.padding(top=20.dp, start = 10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.microphone), tint = Color.White,
                contentDescription = null,
//                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(55.dp)
                    //                .padding(6.dp)
                    .clip(CircleShape)
                    .background(Color(0xFF006257))
                    .border(2.dp, Color(0xFF006257), CircleShape)
                    .padding(16.dp)
                    .background(Color(0xFF006257))
                ,

            )
        }
    }

}

@Composable
private fun GradientButton(
    gradientColors: List<Color>,
    cornerRadius: Dp,
    nameButton: String,
    roundedCornerShape: RoundedCornerShape
) {

    Button(
        modifier = Modifier
            .fillMaxWidth()
//            .padding(start = 32.dp, end = 32.dp),
                ,
        onClick = {
            //your code
        },

        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent
        ),
        shape = RoundedCornerShape(cornerRadius)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(colors = gradientColors),
                    shape = roundedCornerShape
                )
                .clip(roundedCornerShape)
                /*.background(
                    brush = Brush.linearGradient(colors = gradientColors),
                    shape = RoundedCornerShape(cornerRadius)
                )*/
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(start = 15.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = nameButton,
                fontSize = 15.sp,
                color = Color.Black
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainUI(navController: NavController) {
    Column(
        Modifier
            .fillMaxSize()
            ) {
        val openDialog = remember { mutableStateOf(false)  }
        val searchbox = remember { mutableStateOf(false)  }
        var selectedTab by remember { mutableStateOf("Chats") }
        if (openDialog.value) {
            AlertDialog(
                containerColor = Color(0xFF006257),
                iconContentColor = Color.White,
                onDismissRequest = { openDialog.value = false },
                title = { Text(text = "Exit Whatsapp") },
                text = { Text("You want to Exit?") },
                confirmButton = { Button(onClick = {exitProcess(0) }) {Text("Yes") } },
                dismissButton = { Button(onClick = { openDialog.value = false}) {Text("Cancel") } })
        }
        val displayName = "Whatsapp"

        if(searchbox.value) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF006257))
            ) {
                var value by remember {
                    mutableStateOf("")
                }
                OutlinedTextField(
                    modifier = Modifier
                        .padding(20.dp)
                        .weight(1f)
                        .border(
                            BorderStroke(width = 2.dp, color = Color.White),
                        ),
                    value = value,
                    onValueChange = { newText ->
                        value = newText
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier.clickable(onClick = {searchbox.value = false}),
                            tint = Color.White
                        )
                    },
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Person Icon", tint = Color.White
                        )
                    },
                    placeholder = { Text(text = "Search") }
                )
            }
        }
        else {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF006257))
        ) {

            Text(
                text = displayName,
                Modifier
                    .padding(20.dp)
                    .weight(1f)
                    ,
                fontSize = 22.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Row(Modifier.padding(end = 15.dp)) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "", modifier = Modifier.clickable(onClick = { searchbox.value = true }), tint = Color.White)
                Icon(painter = painterResource(id = R.drawable.newchatlogo), contentDescription = "",
                    Modifier
                        .padding(start = 14.dp, end = 14.dp)
                        .size(25.dp), tint = Color.White)
                Icon(painter = painterResource(id = R.drawable.noun_three_dots_2854151), contentDescription = "",
                    Modifier
                        .size(25.dp)
                        .clickable(onClick = { openDialog.value = true }), tint = Color.White)
            }
        }}
        val menustyle = TextStyle(fontWeight = FontWeight.Bold,fontSize = 18.sp, color = Color.White)
        val menudesign = TextStyle(fontWeight = FontWeight.Bold,fontSize = 15.sp, color = Color.LightGray)

        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF006257))) {

            Text(
                text = "CALLS",
                modifier = Modifier
                    .padding(20.dp)
                    .clickable { selectedTab = "Calls" },
                style = if (selectedTab == "Calls") menustyle else menudesign
            )
            Text(
                text = "CHATS",
                style = if (selectedTab == "Chats") menustyle else menudesign,
                modifier = Modifier.clickable(onClick = { selectedTab = "Chats" })
            )
            Text(
                text = "CONTACTS",
                style = if (selectedTab == "Contacts") menustyle else menudesign,
                modifier = Modifier
                    .padding(end = 20.dp)
                    .clickable(onClick = { selectedTab = "Contacts" })
            )
        }
        Box(Modifier.weight(1f)) {
            val transition = updateTransition(targetState = selectedTab, label = "Tab Transition")
            val contentModifier = Modifier.fillMaxSize()
            Crossfade(
                targetState = transition.targetState,
                modifier = contentModifier,
                animationSpec = tween(durationMillis = 300)
            ) { tab ->
                when (tab) {
                    "Calls" -> CallView()
                    "Chats" -> ChatsView(navController)
                    "Contacts" -> ContactView()
                }
            }
            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd)
                    ,
                onClick = {
                          openDialog.value = true
                }, containerColor = Color(0xFF006257)
            ) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add", tint = Color.White)
            }
        }
    }
}

@Composable
fun ContactView() {
    LazyColumn(modifier = listModifier) {
        items(contactnames) { contact ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.cat), contentDescription = "", contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Gray, CircleShape))
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                    Text(text = contact, style = textStyle, color = Color.Black, fontWeight = FontWeight.Bold)


                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider(Modifier.padding(start = 75.dp))
        }
    }
}

private val contactnames =
    mutableListOf("Alvarez", "Grealish", "Rashford", "Stefan", "Haaland", "Akanji", "Ederson", "Walker", "Vini", "Kevin")
val quotes = listOf(
    "The only way to do great work is to love what you do - Steve Jobs",
    "Success is not the key to happiness. Happiness is the key to success - Albert Schweitzer",
    "The future belongs to those who believe in the beauty of their dreams - Eleanor Roosevelt",
    "In the middle of every difficulty lies opportunity - Albert Einstein",
    "Believe you can and you're halfway there - Theodore Roosevelt"
)

private val listModifier = Modifier
    .fillMaxSize()
    .padding(10.dp)

private val textStyle = TextStyle(fontSize = 15.sp, color = Color.White)



@Composable
fun ChatsView(navController: NavController) {
    val contactWithRandomQuote = remember { contactnames.map { contact -> contact to quotes.random() } }
    val maxCharacters = 40
    LazyColumn(modifier = listModifier) {
        items(contactWithRandomQuote) { (contact, randomQuote) ->
                val displayedQuote = if (randomQuote.length <= maxCharacters) {
                    randomQuote
                } else {
                    randomQuote.substring(0, maxCharacters) + "..."
                }
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable(onClick = {
                            navController.navigate(
                                Screen.DetailScreen.withArgs(
                                    contact,
                                    randomQuote
                                )
                            )
                        }), verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.cat), contentDescription = "", contentScale = ContentScale.Crop,            // crop the image if it's not a square
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)                       // clip to the circle shape
                        .border(2.dp, Color.Gray, CircleShape))
                Spacer(modifier = Modifier.width(20.dp))
                Column {
                Text(text = contact, style = textStyle, color = Color.Black, fontWeight = FontWeight.Bold)
                Text(text = displayedQuote, style = textStyle, color = Color.Gray)

                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider(Modifier.padding(start = 75.dp))
        }
    }
}
@Composable
fun CallView() {
    LazyColumn(modifier = listModifier) {
        items(contactnames) { contact ->
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.pfp1),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,            // crop the image if it's not a square
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)                       // clip to the circle shape
                            .border(2.dp, Color.Gray, CircleShape)
                    )
                    Spacer(modifier = Modifier.width(20.dp))
                    Column {
                        Text(
                            text = contact,
                            style = textStyle,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                        Row(verticalAlignment = Alignment.Bottom) {
                            Icon(
                                painter = painterResource(id = R.drawable.calloutgoing),
                                contentDescription = "",
                                tint = Color(0xFF006257),
                                modifier = Modifier.size(15.dp)
                            )
                            Spacer(modifier = Modifier.width(5 .dp))
                            Text(text = "Today", style = textStyle, color = Color.Gray)
                        }
                    }
                }
                Icon(imageVector = Icons.Filled.Call, contentDescription = "", tint = Color(0xFF006257))
            }
            Spacer(modifier = Modifier.height(15.dp))
            Divider(Modifier.padding(start = 75.dp))
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun Bars() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text("I'm a TopAppBar")
            },
            navigationIcon = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }, actions = {
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Share, null)
                }
                IconButton(onClick = {/* Do Something*/ }) {
                    Icon(Icons.Filled.Settings, null)
                }
            })

        Text("Hello World")

    }
}

@Preview(showBackground = true)
@Composable
fun WhatsappPreview() {
    WhatsappCloneTheme {
        val navController = rememberNavController()
//        Navigation()
        MainUI(navController = navController)
//        DetailScreen(navController = navController, name = "",quote = "")
    }
}