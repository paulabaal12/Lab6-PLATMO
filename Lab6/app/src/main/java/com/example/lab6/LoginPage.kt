package com.example.lab6
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab6.ui.theme.Lab6Theme
class LoginPage : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Lab6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginPageContent()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPageContent() {
    var usuario by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    val maxUsuarioLength = 10
    val maxContraseñaLength = 8

    val textFieldModifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .background(Color.White.copy(alpha = 0.5f))

    val textFieldFontSize = 25.sp

    val context = LocalContext.current // Mover esta línea aquí

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.fondomoradito),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.5f))
                .blur(
                    radiusX = 3.dp,
                    radiusY = 3.dp,
                    edgeTreatment = BlurredEdgeTreatment(RoundedCornerShape(8.dp))
                )
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.login),
                contentDescription = "Bienvenido",
                modifier = Modifier
                    .size(230.dp)
                    .padding(20.dp)
            )

            TextField(
                value = usuario,
                onValueChange = {
                    if (it.length <= maxUsuarioLength)
                        usuario = it
                },
                modifier = textFieldModifier,
                placeholder = { Text("Usuario") },
                textStyle = TextStyle(fontSize = textFieldFontSize)
            )

            PasswordTextField(
                password = contraseña,
                onPasswordChange = {
                    if (it.length <= maxContraseñaLength)
                        contraseña = it
                },
                modifier = textFieldModifier,
                passwordVisible = passwordVisible,
                onPasswordVisibilityChange = { passwordVisible = it },
                fontSize = textFieldFontSize
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    if (usuario == "Pauliss13" && contraseña == "lec1655") {
                        val intent = Intent(context, Galeria::class.java)
                        context.startActivity(intent)
                    } else {
                        // Mostrar mensaje de usuario o contraseña inválida
                        Toast.makeText(context, "Usuario o contraseña inválida", Toast.LENGTH_SHORT).show()
                    }
                }
            ) {
                Text(text = "Login", style = TextStyle(
                    fontSize = textFieldFontSize,
                    fontWeight = FontWeight.Bold))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextField(
    password: String,
    onPasswordChange: (String) -> Unit,
    passwordVisible: Boolean,
    onPasswordVisibilityChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    fontSize: TextUnit
) {
    Column(
        modifier = modifier,
    ) {
        TextField(
            value = password,
            placeholder = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            onValueChange = { newValue ->
                onPasswordChange(newValue)
            },
            textStyle = TextStyle(fontSize = fontSize),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(4.dp))
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Checkbox(
            checked = passwordVisible,
            onCheckedChange = { isVisible ->
                onPasswordVisibilityChange(isVisible)
            },
            modifier = Modifier.padding(start = 4.dp))
        Text(
            text = "Mostrar Contraseña",
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = TextStyle(
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontFamily = FontFamily.Serif
            )
        )
    }
}

@Preview
@Composable
fun LoginPageContentPreview() {
    Lab6Theme {
        LoginPage()
    }
}
