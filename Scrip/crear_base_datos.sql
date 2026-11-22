
CREATE DATABASE InventarioDB;
GO


USE InventarioDB;
GO


CREATE TABLE Clientes (
    IdCliente INT PRIMARY KEY IDENTITY(1,1),
    Nombre VARCHAR(100) NOT NULL,
    Ciudad VARCHAR(50),
    Telefono VARCHAR(20)
);


CREATE TABLE Productos (
    IdProducto INT PRIMARY KEY IDENTITY(1,1),
    NombreProducto VARCHAR(100) NOT NULL,
    Precio DECIMAL(10,2),
    Stock INT
);


CREATE TABLE Ventas (
    IdVenta INT PRIMARY KEY IDENTITY(1,1),
    IdCliente INT,
    IdProducto INT,
    Cantidad INT,
    FechaVenta DATE,
    FOREIGN KEY (IdCliente) REFERENCES Clientes(IdCliente),
    FOREIGN KEY (IdProducto) REFERENCES Productos(IdProducto)
);


INSERT INTO Clientes (Nombre, Ciudad, Telefono)
VALUES
('Juan Perez', 'Medellin', '3001234567'),
('Maria Lopez', 'Bogota', '3012345678'),
('Carlos Gomez', 'Cali', '3023456789'),
('Ana Rodriguez', 'Barranquilla', '3034567890'),
('Luis Martinez', 'Bucaramanga', '3045678901');


INSERT INTO Productos (NombreProducto, Precio, Stock)
VALUES
('Teclado', 80000, 20),
('Mouse', 40000, 35),
('Monitor', 650000, 10),
('Portatil', 2800000, 5),
('Memoria USB 64GB', 45000, 50);


INSERT INTO Ventas (IdCliente, IdProducto, Cantidad, FechaVenta)
VALUES
(1, 1, 2, '2026-11-01'),
(2, 3, 1, '2026-11-02'),
(3, 2, 3, '2026-11-03'),
(4, 5, 2, '2026-11-04'),
(5, 4, 1, '2026-11-05');
GO

 