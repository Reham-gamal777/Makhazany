# Exports Feature Implementation

This document describes the implementation of the "الصادرات" (Exports) screen following Clean Architecture principles.

## Project Structure

```
app/src/main/java/com/example/makhazany/
├── core/
│   ├── di/
│   │   └── ExportsDataModule.kt
│   └── util/
│       └── Result.kt
├── features/exports/
│   ├── data/
│   │   ├── datasource/
│   │   │   ├── ExportsLocalDataSource.kt
│   │   │   └── ExportsLocalDataSourceImpl.kt
│   │   ├── model/
│   │   │   └── ExportInvoiceDto.kt
│   │   ├── mapper/
│   │   │   └── ExportsMappers.kt
│   │   └── ExportsRepositoryImpl.kt
│   ├── domain/
│   │   ├── entity/
│   │   │   └── ExportInvoice.kt
│   │   ├── repository/
│   │   │   └── ExportsRepository.kt
│   │   └── usecase/
│   │       ├── GetExportsUseCase.kt
│   │       └── GetExportsSummaryUseCase.kt
│   └── presentation/
│       ├── ExportsRoute.kt
│       ├── ExportsScreen.kt
│       ├── ExportsViewModel.kt
│       ├── ExportsUiState.kt
│       └── components/
│           ├── SearchBar.kt
│           ├── ExportsTable.kt
│           └── SummaryCards.kt
```

## Local Data Files

The feature uses JSON files stored in `app/src/main/assets/exports/`:

### exports.json
Contains the list of export invoices:
```json
{
  "items": [
    {
      "id": "1250",
      "exportNumber": 1250,
      "clientName": "اسم العميل",
      "exportDate": "2026-03-27",
      "amount": 2500.0,
      "currency": "ج.م"
    }
  ]
}
```

### summary.json
Contains summary statistics:
```json
{
  "totalSalesToday": 19450.0,
  "invoicesCount": 5,
  "activeCustomersCount": 4,
  "currency": "ج.م"
}
```

## How to Add More Invoices

1. Open `app/src/main/assets/exports/exports.json`
2. Add new invoice objects to the `items` array
3. Each invoice should have:
   - `id`: Unique string identifier
   - `exportNumber`: Invoice number (integer)
   - `clientName`: Client name in Arabic
   - `exportDate`: Date in YYYY-MM-DD format
   - `amount`: Amount as double
   - `currency`: Currency code (e.g., "ج.م")

Example:
```json
{
  "id": "1255",
  "exportNumber": 1255,
  "clientName": "عميل جديد",
  "exportDate": "2026-04-01",
  "amount": 3200.0,
  "currency": "ج.م"
}
```

## How to Update Summary

1. Open `app/src/main/assets/exports/summary.json`
2. Update the values:
   - `totalSalesToday`: Total sales amount
   - `invoicesCount`: Number of invoices
   - `activeCustomersCount`: Number of active customers
   - `currency`: Currency code

## Architecture Overview

### Data Layer
- **DTOs**: `ExportInvoiceDto`, `ExportsPageDto`, `ExportsSummaryDto`
- **DataSource**: `ExportsLocalDataSource` reads JSON from assets
- **Repository**: `ExportsRepositoryImpl` handles business logic and filtering

### Domain Layer
- **Entities**: Pure Kotlin data classes (`ExportInvoice`, `ExportsPage`, `ExportsSummary`)
- **Repository Interface**: `ExportsRepository`
- **Use Cases**: `GetExportsUseCase`, `GetExportsSummaryUseCase`

### Presentation Layer
- **ViewModel**: `ExportsViewModel` with `StateFlow<ExportsUiState>`
- **UI**: `ExportsScreen` with RTL support and Arabic text
- **Components**: Reusable UI components

## Key Features

- ✅ RTL (Right-to-Left) layout for Arabic
- ✅ Search by invoice number or client name
- ✅ Debounced search (400ms)
- ✅ Local pagination support
- ✅ Loading, error, and empty states
- ✅ Summary cards with statistics
- ✅ Clean Architecture separation
- ✅ Dependency injection with Hilt
- ✅ JSON parsing with kotlinx.serialization

## Running the Feature

1. Ensure the JSON files exist in `app/src/main/assets/exports/`
2. The feature is ready to use - no additional setup required
3. Data is loaded from assets on app startup

## Testing

Unit tests can be added for:
- Use cases with mocked repositories
- ViewModel state management
- Data mappers
- JSON parsing

Example test structure:
```
app/src/test/java/com/example/makhazany/features/exports/
├── data/
├── domain/
└── presentation/
```
