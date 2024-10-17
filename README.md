# Solución a los Diferentes Retos - Tópicos Avanzados de Ingeniería de Software

## Taller 1

En este taller, se puede seleccionar uno de los retos y completarlos. Para mi solución, hice los retos 1, reto 2, reto iFrame, y reto tab. El código de los retos se encuentra en la carpeta `TallerSelenium`, en la cual se implementó una clase para cada uno de los retos.

A continuación, se presentan los diferentes retos y su clase relacionada:

**Reto #1**  
Requisitos: [Reto 1](https://drive.google.com/file/d/1YI98bM5R6yNbC6cOVAuOeKDXg0p6zCkP/view)  
Página web: https://teststore.automationtesting.co.uk/index.php  

**Reto #2**  
Requisitos: [Reto 2](https://drive.google.com/file/d/1EIPI1NoGWxeFegPcL6tQXst6GWkG3PlM/view)  
Página web: https://www.saucedemo.com/  

**Retos Adicionales**  
- Reto iFrame: [Requisito](https://automationtesting.co.uk/iframes.html)  
  Página web: https://automationtesting.co.uk/iframes.html  

- Reto Tab: [Requisito](https://drive.google.com/file/d/1lYnalF_gNhuNdGp2WkvcAdzCY5jQ0_uH/view)  
  Página web: https://automationtesting.co.uk/browserTabs.html  


## Taller 2

Este taller se realiza usando **Playwright** con **POM (Page Object Model)** y **Data Driven Testing**, y se encuentra en la carpeta `TallerPlaywright`.

### Tecnologías Utilizadas
- **Playwright**: Para la automatización de pruebas en la web.
- **TypeScript**: Lenguaje utilizado para su sintaxis estricta y capacidades de tipado.
- **Page Object Model (POM)**: Se utiliza para estructurar las páginas y hacer el código más mantenible y reutilizable.
- **Data Driven Testing**: Para probar diferentes datos y hacer que las pruebas sean más flexibles y robustas.

### Estructura del Proyecto
- **Carpeta `pages`**: Contiene las clases POM que representan cada página de la aplicación. Cada clase define selectores y métodos para interactuar con los elementos de esa página específica.
- **Carpeta `models`**: Incluye las clases que representan las estructuras de datos utilizadas en el proyecto, como `TeamData` y `Pokemon`.
- **Carpeta `data`**: Almacena archivos JSON utilizados para la prueba orientada a datos, permitiendo variar la entrada sin modificar el código de las pruebas.
- **Carpeta `tests`**: Contiene el test principal, que utiliza las clases de POM y datos de entrada desde JSON para verificar el flujo completo de creación y validación de un equipo de Pokémon.

### Descripción del Flujo de Pruebas
1. **HomePage**: Navega al `Teambuilder`.
2. **TeamCreationPage**: Selecciona el formato de batalla y crea un nuevo equipo.
3. **TeamListPage**: Agrega Pokémon al equipo.
4. **PokemonDetailsPage**: Rellena los detalles de cada Pokémon (nombre, ítem, movimientos, EVs, etc.) basándose en los datos proporcionados en el JSON.
5. **Validación**: Al final de la prueba, se valida que el equipo cumple con los requisitos del formato seleccionado.

### Ejecución de las Pruebas
Para ejecutar las pruebas, asegúrate de tener **Node.js** instalado y sigue estos pasos:

1. **Instala Playwright**:

   ```bash
   npm install @playwright/test
   ```

2. **Ejecuta las pruebas**:

   ```bash
   npx playwright test
   ```

3. **Ejecuta en modo ui o headed**:
   - **Headless**: Para un rendimiento más rápido, sin interfaz gráfica.
   
     ```bash
     npx playwright test --ui
     ```

   - **Headed**: Para observar la ejecución de las pruebas en tiempo real.

     ```bash
     npx playwright test --headed
     ```
