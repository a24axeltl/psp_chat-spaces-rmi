# Chat P2P simple empleando Java RMI  

## Descripción
Proyecto que implementa un **sistema de chat punto a punto (P2P)** utilizando **Java RMI (Remote Method Invocation)**.  
Dos aplicaciones Java independientes, ejecutándose en **JVM distintas**, se comunican entre sí mediante **invocaciones remotas**, pudiendo ser **cliente y servidor**.

El programa es capaz de...
- Publicar un **servicio** para recibir mensajes.
- Localiza el objeto remoto de la otra instancia mediante el **RMI Registry**.
- Permite enviar y recibir mensajes de forma bidireccional y concurrente.

El programa **NO** es capaz de...
-Guardar el historial de un chat.
-Almacenar diferentes chats.
-Seleccionar differentes chats privados.
-Indicar mensajes **no vistos**.

## Arquitectura general
El sistema sigue un modelo simétrico **P2P**:


┌──────────────┐ RMI ┌──────────────┐
│ Chat A │ ◀───── ──────▶ │ Chat B │
│ │ │ │                       | | | |
│ Servidor RMI │     │ Servidor RMI │
│ Cliente RMI  │     │ Cliente RMI  │
└──────────────┘     └──────────────┘
