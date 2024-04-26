import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState


@Composable
fun SecondaryWindow(onClose: () -> Unit) {
    val secondaryWindowState = rememberWindowState()

    Window(
        onCloseRequest = { onClose() } ,
        title = "Ventana Secundaria",
        state = secondaryWindowState
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
        Text("Este es el contenido de la ventana secundaria", color = Color.Red)

        Button(
            onClick = onClose
        ){ Text("Volver a la ventana principal") }
        }
    }
}


fun main() = application{

    val icon = BitmapPainter(useResource("Sample1.png", ::loadImageBitmap))
    val mainWindowState = rememberWindowState()
    var showMainWindow by remember { mutableStateOf(true) }
    var showSecondWindow by remember { mutableStateOf(false) }


    if (showMainWindow) {
        Window(
            onCloseRequest = { showMainWindow = false },
            title = "Ventana Principal",
            icon = icon,
            state = mainWindowState
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Esta es la ventana principal", color = Color.Magenta)

                Button(
                    onClick = {
                        showMainWindow = false
                        showSecondWindow = true
                }) {
                    Text("Abrir Ventana Secundaria y cerrar esta")
                }
            }
        }
    }

    if (showSecondWindow) {
        SecondaryWindow(onClose = { showSecondWindow = false
                                    showMainWindow = true})
    }

    if (!showMainWindow && !showSecondWindow) {
        exitApplication()
    }
}
