# MiniStore V2.0

Sistema de gestión de inventario desarrollado en Java que permite administrar productos, realizar compras y generar reportes utilizando Programación Orientada a Objetos y estructuras de datos.

## Características

- Gestión completa de inventario (agregar, listar, buscar productos)
- Sistema de compras con validación de stock
- Generación de tickets de compra individuales y finales
- Estadísticas de productos (más caro, más barato)
- Búsqueda avanzada con coincidencias parciales
- Interfaz gráfica con JOptionPane
- Validaciones robustas y manejo de excepciones

## Tecnologías

- Java 13+
- Swing (JOptionPane)
- ArrayList y HashMap para gestión de datos

## Estructura del Proyecto

```
src/
├── App.java              # Clase principal con menú interactivo
└── domain/
    ├── Inventory.java    # Gestor de inventario y operaciones
    ├── Product.java      # Clase abstracta base
    ├── Food.java         # Subclase para productos alimenticios
    └── Appliance.java    # Subclase para electrodomésticos
```

## Clases Principales

### Product (Abstracta)
Clase base que define la estructura común de todos los productos.

**Atributos:**
- `id`: Identificador único autogenerado
- `name`: Nombre del producto
- `price`: Precio unitario

**Métodos:**
- `getDescription()`: Método abstracto implementado por subclases

### Food
Productos alimenticios con fecha de vencimiento.

**Atributos adicionales:**
- `category`: Categoría del alimento
- `expirationDate`: Fecha de vencimiento

### Appliance
Electrodomésticos con garantía.

**Atributos adicionales:**
- `category`: Categoría del electrodoméstico
- `warrantyMonths`: Meses de garantía

### Inventory
Gestor principal del sistema que administra productos y operaciones.

**Estructuras de datos:**
- `ArrayList<Product>`: Lista de productos
- `HashMap<String, Integer>`: Stock por nombre de producto
- `ArrayList<String>`: Historial de compras

**Métodos principales:**
- `addProduct()`: Agrega productos al inventario
- `showInventory()`: Lista todos los productos con stock
- `searchProduct()`: Búsqueda por coincidencias parciales
- `buyProduct()`: Procesa compras con validación de stock
- `getStatistics()`: Genera estadísticas de precios
- `getFinalTicket()`: Genera ticket final de sesión

## Funcionalidades

### 1. Agregar Producto
- Solicita tipo de producto (Food o Appliance)
- Valida datos de entrada
- Verifica productos duplicados
- Agrega al inventario con stock inicial

### 2. Mostrar Inventario
- Lista todos los productos disponibles
- Muestra información detallada de cada producto
- Incluye stock disponible

### 3. Comprar Producto
- Busca producto por nombre (case-insensitive)
- Valida stock disponible
- Actualiza inventario automáticamente
- Genera ticket de compra
- Acumula para ticket final

### 4. Estadísticas
- Identifica producto más caro
- Identifica producto más barato
- Muestra precios comparativos

### 5. Buscar Producto
- Búsqueda por coincidencias parciales
- Insensible a mayúsculas/minúsculas
- Muestra todos los productos que coincidan

### 6. Salir
- Genera ticket final con todas las compras
- Muestra total acumulado de la sesión
- Cierra la aplicación

## Validaciones

El sistema incluye validaciones en múltiples capas:

**Validaciones de UI:**
- Campos vacíos o nulos
- Operaciones canceladas por el usuario

**Validaciones de negocio:**
- Precios y cantidades positivas
- Stock suficiente para compras
- Productos duplicados
- Formato de números válidos

**Manejo de excepciones:**
- `NumberFormatException`: Entradas numéricas inválidas
- `IllegalArgumentException`: Violaciones de reglas de negocio

## Conceptos de POO Aplicados

- **Abstracción**: Clase abstracta Product con método abstracto
- **Herencia**: Food y Appliance extienden Product
- **Encapsulamiento**: Atributos privados con getters/setters
- **Polimorfismo**: Implementación diferente de getDescription()

## Cómo Ejecutar

1. Compila todos los archivos Java:
```bash
javac *.java
```

2. Ejecuta la aplicación:
```bash
java App
```

3. Sigue las instrucciones en los diálogos de JOptionPane

## Ejemplo de Uso

1. **Agregar productos**:
   - Arroz Premium (Food): $5.00
   - Licuadora (Appliance): $80.00

2. **Realizar compras**:
   - Comprar 10 unidades de Arroz Premium
   - Comprar 2 unidades de Licuadora

3. **Ver estadísticas**:
   - Más caro: Licuadora ($80.00)
   - Más barato: Arroz Premium ($5.00)

4. **Salir**:
   - Ticket final muestra todas las compras
   - Total: $210.00

## Características Técnicas

- IDs autogenerados únicos para cada producto
- Búsquedas case-insensitive con normalización
- Sincronización automática entre ArrayList y HashMap
- Acumulación de compras para reportes de sesión
- Mensajes de error descriptivos

## Mejoras Futuras

- Persistencia de datos en archivos o base de datos
- Interfaz gráfica completa con Swing/JavaFX
- Sistema de usuarios y autenticación
- Reportes avanzados (productos más vendidos, ganancias)
- Categorías dinámicas de productos
- Sistema de descuentos y promociones

## Autor

Proyecto desarrollado como parte del aprendizaje de POO y estructuras de datos en Java.

## Licencia

Proyecto educativo - Uso libre para aprendizaje.