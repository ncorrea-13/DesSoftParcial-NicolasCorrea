<div align="center">

# ParcialBackDesarrollo

</div>

# Presentación

Soy Nicolás Correa, estudiante de Ingeniería en Sistemas de Información. Este proyecto es una API desarrollada en Java que permite identificar mutantes a partir de secuencias de ADN y calcular estadísticas sobre las mismas. La aplicación está diseñada para responder a solicitudes HTTP y se desarrolló utilizando las mejores prácticas de desarrollo de software backend en Java.

# Estructura del Proyecto

- **ParcialUnoApplication.java**: Clase principal de la aplicación.
- **MutantController.java** y **StatsController.java**: Controladores para los endpoints de la API.
- **MutantService**: Contiene la lógica para determinar la posibilidad del mutante
- **ValidationService**: Usa la lógica para validar que sea un ADN analizable
- **DnaRepository**: Entablece la relación en conjunto de la interfaz de JPARepository y la Base de Datos H2
- **src/main/dto**: Crea DTOs para poder comunicarse quien envía la petición y encapsula los JSON.
- **src/main/resources**: Incluye archivos de configuración como `application.properties`.
- **src/test/java**: Pruebas unitarias y de integración para verificar el funcionamiento de la aplicación.

# Aplicación

1. **Iniciación**:
   - Acceda al contenedor usando:
https://parcialbackdesarrollo.onrender.com

2. **Endpoints Principales**:
   - **POST** `/mutant`: Verifica si una secuencia de ADN pertenece a un mutante.
   - **GET** `/stats`: Obtiene estadísticas sobre las secuencias procesadas.


# Ejemplo de solicitud en Postman
- Cree la solicitud POST en Postman
- Ingresa la URL de la API en render: https://parcialbackdesarrollo.onrender.com/mutant
- En el Body, utilize raw y JSON. Luego ingrese:
{
  "dna": [ "AAAA", "CCCC", "TGAC", "GGTC" ]
}

Para más detalles sobre la estructura de los endpoints y el manejo de datos, revisa el código en la carpeta `src/main/java`.
