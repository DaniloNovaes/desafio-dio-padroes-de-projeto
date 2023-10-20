# desafio-dio-padroes-de-projeto

## Diagrama de Classes

```mermaid
classDiagram
    class Product {
        +Integer id
        +String productName
        +Double price
        +String date
        +Double desiredPrice
        +String productLink
    }
    class ProductController {
        +ProductService_ productService
        +Product getFromWishList(Integer id)
        +Product addToWishList(Product product)
        +Product updateWishList(Product product)
        +String deleteFromWishList(Integer id)
    }
    class ProductService_ {
        +Product getFromWishList(Integer id)
        +Product addToWishList(Product product)
        +Product updateWishList(Product product)
        +String deleteFromWishList(Integer id)
    }
    class ProductRepository {
        +Product save(Product product)
        +Product findById(Integer id)
        +Iterable<Product> findAll()
        +void deleteById(Integer id)
    }
    ProductController --> ProductService_
    ProductService_ --> ProductRepository
    Product -->|> Serializable
```
