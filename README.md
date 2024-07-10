# Conversor de Divisas
Este proyecto es un conversor de divisas basado en Java que permite convertir montos entre diferentes monedas usando la API de Exchangerate. El programa proporciona un menú interactivo para que el usuario seleccione la conversión deseada y luego ingrese el monto a convertir. Las conversiones disponibles incluyen USD a MXN, MXN a USD, EUR a MXN, MXN a EUR, USD a EUR y EUR a USD.

## Características
Conversión de montos entre USD, MXN y EUR.
Menú interactivo para seleccionar la conversión deseada.
Uso de la API de Exchangerate para obtener las tasas de conversión actuales.
Manejo de excepciones para errores en la solicitud HTTP.
Requisitos
JDK 11 o superior
Biblioteca Gson

## Uso
Compila el proyecto:
bash
Copiar código
javac -cp gson-2.8.8.jar:. Main.java

## Ejecuta el programa:
bash
Copiar código
java -cp gson-2.8.8.jar:. Main
Sigue las instrucciones en pantalla para seleccionar la conversión deseada e ingresa el monto a convertir.

## API Key
Este proyecto utiliza la API de Exchangerate para obtener las tasas de conversión. Asegúrate de reemplazar "6703eb13a387649146aff318" en la URL con tu propia API Key de Exchangerate.

## Contribuir
Si deseas contribuir a este proyecto, por favor sigue estos pasos:

Haz un fork del repositorio.
Crea una rama (git checkout -b feature/nueva-funcionalidad).
Realiza tus cambios y haz commit de ellos (git commit -am 'Agregar nueva funcionalidad').
Empuja tus cambios (git push origin feature/nueva-funcionalidad).
Abre un Pull Request.

## Licencia
Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo LICENSE para más detalles.
