# Cibus Server - Controllers API Documentation

## Base URL
`http://localhost:8080`

---

## UserController (`/user`)

### GET `/user`
Lista todos os usuários com paginação e filtros.

**Query Parameters:**
- `page` (int): Número da página
- `quantity` (int): Quantidade por página
- `name` (string, opcional): Filtro por nome
- `login` (string, opcional): Filtro por login

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "name": "João Silva",
      "login": "joao.silva",
      "isFirstLogin": false,
      "profile": "ROLE_ADMIN"
    }
  ],
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0
}
```

### GET `/user/{usuarioId}`
Busca usuário por ID.

**Response:**
```json
{
  "id": 1,
  "name": "João Silva",
  "login": "joao.silva",
  "isFirstLogin": false,
  "profile": "ROLE_ADMIN"
}
```

### POST `/user`
Cria um novo usuário.

**Request Body:**
```json
{
  "name": "Maria Santos",
  "login": "maria.santos",
  "pass": "senha123",
  "profile": "ROLE_USER"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Maria Santos",
  "login": "maria.santos",
  "isFirstLogin": true,
  "profile": "ROLE_USER"
}
```

### PUT `/user/{usuarioId}`
Atualiza um usuário existente.

**Request Body:**
```json
{
  "name": "Maria Santos Silva",
  "login": "maria.santos",
  "profile": "ROLE_ADMIN"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Maria Santos Silva",
  "login": "maria.santos",
  "isFirstLogin": false,
  "profile": "ROLE_ADMIN"
}
```

### DELETE `/user/{usuarioId}`
Remove um usuário.

**Response:**
```json
true
```

---

## CategoryController (`/category`)

### GET `/category`
Lista todas as categorias com paginação e filtros.

**Query Parameters:**
- `page` (int): Número da página
- `quantity` (int): Quantidade por página
- `name` (string, opcional): Filtro por nome
- `active` (DomainActive, opcional): Filtro por status (YES/NO/BOUTH)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Bebidas",
      "active": true,
      "icon": "drink-icon",
      "subCategories": []
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

### GET `/category/{categoriaId}`
Busca categoria por ID.

**Response:**
```json
{
  "id": 1,
  "name": "Bebidas",
  "active": true,
  "icon": "drink-icon",
  "subCategories": []
}
```

### POST `/category`
Cria uma nova categoria.

**Request Body:**
```json
{
  "name": "Pratos Principais",
  "active": true,
  "icon": "food-icon"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Pratos Principais",
  "active": true,
  "icon": "food-icon",
  "subCategories": []
}
```

### PUT `/category/{categoriaId}`
Atualiza uma categoria existente.

**Request Body:**
```json
{
  "name": "Pratos Principais Atualizados",
  "active": false,
  "icon": "new-food-icon"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Pratos Principais Atualizados",
  "active": false,
  "icon": "new-food-icon",
  "subCategories": []
}
```

### DELETE `/category/{categoriaId}`
Remove uma categoria.

**Response:**
```json
true
```

### GET `/category/valuelabel`
Lista todas as categorias em formato value/label.

**Response:**
```json
[
  {
    "id": 1,
    "name": "Bebidas"
  },
  {
    "id": 2,
    "name": "Pratos Principais"
  }
]
```

### GET `/category/actives/valuelabel`
Lista apenas categorias ativas em formato value/label.

**Response:**
```json
[
  {
    "id": 1,
    "name": "Bebidas"
  }
]
```

---

## ProductController (`/product`)

### GET `/product`
Lista todos os produtos com paginação e filtros.

**Query Parameters:**
- `page` (int): Número da página
- `quantity` (int): Quantidade por página
- `name` (string, opcional): Filtro por nome
- `subCategoryId` (long, opcional): Filtro por subcategoria
- `active` (DomainActive, opcional): Filtro por status (YES/NO/BOUTH)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Pizza Margherita",
      "description": "Pizza com molho de tomate e mussarela",
      "price": 25.90,
      "stockQuantity": 50,
      "minimumStock": 5,
      "isActive": true,
      "visible": true,
      "imagePath": "/images/pizza-margherita.jpg",
      "subCategories": [],
      "variations": []
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

### GET `/product/category/{categoryId}`
Lista produtos por categoria.

**Response:**
```json
[
  {
    "id": 1,
    "name": "Pizza Margherita",
    "description": "Pizza com molho de tomate e mussarela",
    "price": 25.90,
    "stockQuantity": 50,
    "minimumStock": 5,
    "isActive": true,
    "visible": true,
    "imagePath": "/images/pizza-margherita.jpg",
    "subCategories": [],
    "variations": []
  }
]
```

### GET `/product/{produtoId}`
Busca produto por ID.

**Response:**
```json
{
  "id": 1,
  "name": "Pizza Margherita",
  "description": "Pizza com molho de tomate e mussarela",
  "price": 25.90,
  "stockQuantity": 50,
  "minimumStock": 5,
  "isActive": true,
  "visible": true,
  "imagePath": "/images/pizza-margherita.jpg",
  "subCategories": [],
  "variations": []
}
```

### POST `/product`
Cria um novo produto.

**Request Body:**
```json
{
  "name": "Pizza Calabresa",
  "description": "Pizza com calabresa e cebola",
  "price": 28.90,
  "stockQuantity": 30,
  "minimumStock": 3,
  "isActive": true,
  "visible": true,
  "imagePath": "/images/pizza-calabresa.jpg"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Pizza Calabresa",
  "description": "Pizza com calabresa e cebola",
  "price": 28.90,
  "stockQuantity": 30,
  "minimumStock": 3,
  "isActive": true,
  "visible": true,
  "imagePath": "/images/pizza-calabresa.jpg",
  "subCategories": [],
  "variations": []
}
```

### PUT `/product/{produtoId}`
Atualiza um produto existente.

**Request Body:**
```json
{
  "name": "Pizza Calabresa Premium",
  "description": "Pizza com calabresa premium e cebola roxa",
  "price": 32.90,
  "stockQuantity": 25,
  "minimumStock": 3,
  "isActive": true,
  "visible": true,
  "imagePath": "/images/pizza-calabresa-premium.jpg"
}
```

**Response:**
```json
{
  "id": 2,
  "name": "Pizza Calabresa Premium",
  "description": "Pizza com calabresa premium e cebola roxa",
  "price": 32.90,
  "stockQuantity": 25,
  "minimumStock": 3,
  "isActive": true,
  "visible": true,
  "imagePath": "/images/pizza-calabresa-premium.jpg",
  "subCategories": [],
  "variations": []
}
```

### DELETE `/product/{produtoId}`
Remove um produto.

**Response:**
```json
true
```

---

## OrderController (`/order`)

### GET `/order`
Lista todos os pedidos.

**Response:**
```json
[
  {
    "id": 1,
    "cardOrderId": 123,
    "orderDate": "2024-01-15T10:30:00Z",
    "tableId": 5,
    "orderStatus": "OPEN",
    "orderProducts": [
      {
        "id": 1,
        "productId": 1,
        "quantity": 2,
        "unitPrice": 25.90
      }
    ]
  }
]
```

### GET `/order/{orderId}`
Busca pedido por ID.

**Response:**
```json
{
  "id": 1,
  "cardOrderId": 123,
  "orderDate": "2024-01-15T10:30:00Z",
  "tableId": 5,
  "orderStatus": "OPEN",
  "orderProducts": [
    {
      "id": 1,
      "productId": 1,
      "quantity": 2,
      "unitPrice": 25.90
    }
  ]
}
```

### GET `/order/{cardOrderId}/table/{tableId}`
Busca pedido por ID do cartão e mesa.

**Response:**
```json
{
  "id": 1,
  "cardOrderId": 123,
  "orderDate": "2024-01-15T10:30:00Z",
  "tableId": 5,
  "orderStatus": "OPEN",
  "orderProducts": []
}
```

### POST `/order/{orderId}/table/{tableId}`
Abre um novo pedido para uma mesa.

**Response:**
```json
{
  "id": 2,
  "cardOrderId": 124,
  "orderDate": "2024-01-15T11:00:00Z",
  "tableId": 3,
  "orderStatus": "OPEN",
  "orderProducts": []
}
```

### PATCH `/order/{cardOrderId}/table/{tableId}`
Adiciona produtos a um pedido existente.

**Request Body:**
```json
[
  {
    "productId": 1,
    "quantity": 2,
    "unitPrice": 25.90
  },
  {
    "productId": 2,
    "quantity": 1,
    "unitPrice": 28.90
  }
]
```

**Response:**
```json
{
  "id": 1,
  "cardOrderId": 123,
  "orderDate": "2024-01-15T10:30:00Z",
  "tableId": 5,
  "orderStatus": "OPEN",
  "orderProducts": [
    {
      "id": 1,
      "productId": 1,
      "quantity": 2,
      "unitPrice": 25.90
    },
    {
      "id": 2,
      "productId": 2,
      "quantity": 1,
      "unitPrice": 28.90
    }
  ]
}
```

### POST `/order/{orderId}`
Finaliza um pedido (checkout).

**Response:**
```json
{
  "id": 1,
  "cardOrderId": 123,
  "orderDate": "2024-01-15T10:30:00Z",
  "tableId": 5,
  "orderStatus": "CLOSED",
  "orderProducts": [
    {
      "id": 1,
      "productId": 1,
      "quantity": 2,
      "unitPrice": 25.90
    }
  ]
}
```

---

## SaleController (`/sale`)

### GET `/sale`
Lista todas as vendas com paginação e filtros.

**Query Parameters:**
- `page` (int): Número da página
- `quantity` (int): Quantidade por página
- `product` (string, opcional): Filtro por produto
- `date` (string, opcional): Filtro por data
- `saleStatus` (SaleStatus, opcional): Status da venda (PAID/ORDER/BOUTH)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "saleDate": "2024-01-15T12:00:00Z",
      "saleStatus": "PAID",
      "client": {
        "id": 1,
        "name": "João Silva"
      },
      "payment": {
        "id": 1,
        "name": "Cartão de Crédito"
      },
      "cash": {
        "id": 1,
        "description": "Caixa Principal"
      },
      "saleProducts": [
        {
          "id": 1,
          "productId": 1,
          "quantity": 2,
          "unitPrice": 25.90
        }
      ]
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

### POST `/sale`
Registra uma nova venda.

**Request Body:**
```json
{
  "saleStatus": "PAID",
  "client": {
    "id": 1
  },
  "payment": {
    "id": 1
  },
  "cash": {
    "id": 1
  },
  "saleProducts": [
    {
      "productId": 1,
      "quantity": 2,
      "unitPrice": 25.90
    }
  ]
}
```

**Response:**
```json
{
  "id": 2,
  "saleDate": "2024-01-15T13:00:00Z",
  "saleStatus": "PAID",
  "client": {
    "id": 1,
    "name": "João Silva"
  },
  "payment": {
    "id": 1,
    "name": "Cartão de Crédito"
  },
  "cash": {
    "id": 1,
    "description": "Caixa Principal"
  },
  "saleProducts": [
    {
      "id": 2,
      "productId": 1,
      "quantity": 2,
      "unitPrice": 25.90
    }
  ]
}
```

---

## CashController (`/cash`)

### GET `/cash`
Lista registros de caixa com paginação e filtros.

**Query Parameters:**
- `page` (int): Número da página
- `quantity` (int): Quantidade por página
- `user` (string, opcional): Filtro por usuário
- `description` (string, opcional): Filtro por descrição
- `openDate` (string, opcional): Data de abertura
- `closeDate` (string, opcional): Data de fechamento

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "description": "Caixa Principal",
      "openDate": "2024-01-15T08:00:00Z",
      "closeDate": null,
      "startValue": 100.00,
      "currentValue": 250.00,
      "user": {
        "id": 1,
        "name": "João Silva"
      }
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

### GET `/cash/{cashId}`
Busca registro de caixa por ID.

**Response:**
```json
{
  "id": 1,
  "description": "Caixa Principal",
  "openDate": "2024-01-15T08:00:00Z",
  "closeDate": null,
  "startValue": 100.00,
  "currentValue": 250.00,
  "user": {
    "id": 1,
    "name": "João Silva"
  }
}
```

### POST `/cash`
Abre um novo caixa.

**Request Body:**
```json
{
  "description": "Caixa da Manhã",
  "startValue": 150.00,
  "user": {
    "id": 1
  }
}
```

**Response:**
```json
{
  "id": 2,
  "description": "Caixa da Manhã",
  "openDate": "2024-01-15T08:30:00Z",
  "closeDate": null,
  "startValue": 150.00,
  "currentValue": 150.00,
  "user": {
    "id": 1,
    "name": "João Silva"
  }
}
```

### PUT `/cash/{cashId}`
Atualiza registro de caixa.

**Request Body:**
```json
{
  "description": "Caixa da Manhã Atualizado",
  "startValue": 200.00,
  "currentValue": 300.00
}
```

**Response:**
```json
{
  "id": 2,
  "description": "Caixa da Manhã Atualizado",
  "openDate": "2024-01-15T08:30:00Z",
  "closeDate": null,
  "startValue": 200.00,
  "currentValue": 300.00,
  "user": {
    "id": 1,
    "name": "João Silva"
  }
}
```

### PUT `/cash/close/{cashId}`
Fecha um caixa.

**Response:**
```json
{
  "id": 1,
  "description": "Caixa Principal",
  "openDate": "2024-01-15T08:00:00Z",
  "closeDate": "2024-01-15T18:00:00Z",
  "startValue": 100.00,
  "currentValue": 450.00,
  "user": {
    "id": 1,
    "name": "João Silva"
  }
}
```

### DELETE `/cash/{cashId}`
Remove registro de caixa.

**Response:**
```json
true
```

### GET `/cash/all/open`
Lista todos os caixas abertos.

**Response:**
```json
[
  {
    "id": 2,
    "description": "Caixa da Manhã",
    "openDate": "2024-01-15T08:30:00Z",
    "closeDate": null,
    "startValue": 150.00,
    "currentValue": 200.00,
    "user": {
      "id": 1,
      "name": "João Silva"
    }
  }
]
```

---

## PaymentController (`/payment`)

### GET `/payment`
Lista formas de pagamento com paginação e filtros.

**Query Parameters:**
- `page` (int): Número da página
- `quantity` (int): Quantidade por página
- `payment` (string, opcional): Filtro por nome
- `description` (string, opcional): Filtro por descrição
- `active` (DomainActive, opcional): Filtro por status (YES/NO/BOUTH)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "name": "Cartão de Crédito",
      "description": "Pagamento via cartão de crédito",
      "active": true,
      "visible": true
    }
  ],
  "totalElements": 1,
  "totalPages": 1
}
```

### GET `/payment/visible`
Lista apenas formas de pagamento visíveis.

**Response:**
```json
[
  {
    "id": 1,
    "name": "Cartão de Crédito",
    "description": "Pagamento via cartão de crédito",
    "active": true,
    "visible": true
  },
  {
    "id": 2,
    "name": "Dinheiro",
    "description": "Pagamento em dinheiro",
    "active": true,
    "visible": true
  }
]
```

### GET `/payment/{paymentId}`
Busca forma de pagamento por ID.

**Response:**
```json
{
  "id": 1,
  "name": "Cartão de Crédito",
  "description": "Pagamento via cartão de crédito",
  "active": true,
  "visible": true
}
```

### POST `/payment`
Cria nova forma de pagamento.

**Request Body:**
```json
{
  "name": "PIX",
  "description": "Pagamento via PIX",
  "active": true,
  "visible": true
}
```

**Response:**
```json
{
  "id": 3,
  "name": "PIX",
  "description": "Pagamento via PIX",
  "active": true,
  "visible": true
}
```

### PUT `/payment/{paymentId}`
Atualiza forma de pagamento.

**Request Body:**
```json
{
  "name": "PIX Atualizado",
  "description": "Pagamento via PIX com desconto",
  "active": true,
  "visible": false
}
```

**Response:**
```json
{
  "id": 3,
  "name": "PIX Atualizado",
  "description": "Pagamento via PIX com desconto",
  "active": true,
  "visible": false
}
```

### DELETE `/payment/{paymentId}`
Remove forma de pagamento.

**Response:**
```json
true
```

---

## Enums Utilizados

### DomainActive
- `YES` - Ativo
- `NO` - Inativo
- `BOUTH` - Ambos (para filtros)

### OrderStatus
- `OPEN` - Aberto
- `CLOSED` - Fechado

### SaleStatus
- `PAID` - Pago
- `ORDER` - Pedido
- `BOUTH` - Ambos (para filtros)

### Profile
- `ROLE_ADMIN` - Administrador
- `ROLE_USER` - Usuário

---

## Códigos de Status HTTP

- `200 OK` - Requisição bem-sucedida
- `201 Created` - Recurso criado com sucesso
- `400 Bad Request` - Dados inválidos na requisição
- `401 Unauthorized` - Token inválido ou ausente
- `403 Forbidden` - Acesso negado
- `404 Not Found` - Recurso não encontrado
- `500 Internal Server Error` - Erro interno do servidor