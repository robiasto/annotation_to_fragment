# Example App

## Overview
A Spring Boot application demonstrating fragment-based UI composition with annotation-driven configuration. This project showcases modular architecture using Spring Modulith and provides a practical example of combining modern backend practices with a dynamic frontend.

### Key Benefits
- UI composition through annotations
- Modular, maintainable architecture
- Built-in security features

## Quick Start
```bash
# Clone the repository
git clone https://github.com/yourusername/example-app.git

# Start PostgreSQL
docker-compose up -d postgres

# Start the application
./gradlew bootRun --args='--spring.profiles.active=local,init-db'

# Access the application
open http://localhost:8080
```

## Implementation example
### Grid
- [Annotation](./src/main/java/de/robiasto/app/infrastructure/fragment/grid/GridColFragmentConfiguration.java)
- [Fragments](./src/main/java/de/robiasto/app/infrastructure/fragment/grid/fragments) 

## Annotation configuration examples

### User
- [Module](./src/main/java/de/robiasto/app/user/detail)
- [Form configuration](./src/main/java/de/robiasto/app/user/detail/utility/UserFormView.java)
