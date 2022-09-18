# Ejercicio Tecnico "Fravega"

## Para poder correr la aplicacion en local es necesario que corra el siguiente comando

```
    mvn clean spring-boot:run
```
## Los endpoints expuestos por la API son:

## Punto de Retiro

| Tipo | URL |
| --- | --- |
| GET | https://localhost:8080/puntoDeRetiro |
| PUT | https://localhost:8080/puntoDeRetiro/{id} |
| DELETE | https://localhost:8080/puntoDeRetiro/{id} |
| POST | https://localhost:8080/puntoDeRetiro |

### Para las llamadas de POST debe tener un cuerpo con este formato:

```
    {
        capacity: 30,
        longitude: -34.5608601,
        latitude: -58.5235249
    }
```
### Para las llamadas GET se recibira un JSON con el siguiente formato:

```
    {
        "id": 1,
        "longitude": 15.1515,
        "latitude": 13.1515,
        "capacity": 3
    }
```

## Sucursal

| Tipo | URL |
| --- | --- |
| GET | https://localhost:8080/sucursal |
| PUT | https://localhost:8080/sucursal/{id} |
| DELETE | https://localhost:8080/sucursal/{id} |
| POST | https://localhost:8080/sucursal |

### Para las llamadas de POST debe tener un cuerpo con este formato:

```
    {
        address: "alguna direccion 1",
        openFrom: 10,
        openTo: 18,
        longitude: -34.5608601,
        latitude: -58.5235249
    }
```

### Para las llamadas GET se recibira un JSON con el siguiente formato:

```
    {
        "id": 2,
        "longitude": 15.1515,
        "latitude": 13.1515,
        "address": "Balvanera 1553",
        "openFrom": 10.00,
        "openTo": 18.00
    }
```


## Obtener el punto mas cercano

| Tipo | URL |
| --- | --- |
| GET | https://localhost:8080/puntoMasCercano/{longitud}/{latitud} |


```
https://localhost:8080/puntoMasCercano/47.2222/20.77777
```
