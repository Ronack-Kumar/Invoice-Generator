# Invoice Generator

A Java-based invoice generator that accepts user inputs and generates professional invoices in both text and Excel formats.

## Features

- **Interactive Console Input**: Easy-to-use command-line interface for entering invoice data
- **Multiple Output Formats**: Generate invoices in text (.txt) and Excel (.xlsx) formats
- **Professional Layout**: Clean, professional invoice templates with proper formatting
- **Sample Data**: Generate sample invoices for demonstration purposes
- **Flexible Configuration**: Customizable tax rates, company information, and invoice notes
- **Automatic Calculations**: Automatic calculation of subtotals, taxes, and totals
- **No-Maven Option**: Run text-only version without installing Maven

## Available Versions

### Text-Only Version (No Dependencies)
- Runs with just Java installed
- Generates professional text-format invoices
- Perfect for quick testing and basic use
- Uses `InvoiceGeneratorTextApp` class

### Full-Featured Version (Requires Maven)
- Supports both text and Excel formats
- Requires Apache POI library for Excel generation
- Uses `InvoiceGeneratorApp` class
- Professional Excel spreadsheets with formatting

## Requirements

- Java 11 or higher
- Maven 3.6 or higher

## Project Structure

```
src/main/java/com/invoicegen/
├── InvoiceGeneratorApp.java          # Main application class
├── model/
│   ├── Invoice.java                  # Invoice data model
│   ├── Customer.java                 # Customer data model
│   ├── Company.java                  # Company data model
│   └── InvoiceItem.java             # Invoice item data model
├── generator/
│   ├── InvoiceGenerator.java        # Generator interface
│   ├── TextInvoiceGenerator.java    # Text format generator
│   └── ExcelInvoiceGenerator.java   # Excel format generator
├── input/
│   └── ConsoleInputHandler.java     # Console input handler
└── util/
    └── SampleDataGenerator.java     # Sample data utility
```

## Dependencies

- **Apache POI**: For Excel file generation
- **JUnit**: For testing (development)

## Building the Project

1. Make sure you have Maven installed
2. Navigate to the project directory
3. Run the build command:

```bash
mvn clean compile
```

## Running the Application

### Quick Start (Windows - No Maven Required)

For immediate use without setting up Maven:

#### Generate Sample Invoice
```cmd
run-sample-simple.bat
```

#### Generate Custom Invoice (Interactive)
```cmd
run-simple.bat
```

**Note**: These scripts generate text-format invoices only. For Excel support, use the Maven approach below.

### Full Featured (Maven Required)

#### Generate Custom Invoice (Interactive)
```bash
mvn exec:java -Dexec.mainClass="com.invoicegen.InvoiceGeneratorApp"
```

#### Generate Sample Invoice
```bash
mvn exec:java -Dexec.mainClass="com.invoicegen.InvoiceGeneratorApp" -Dexec.args="--sample"
```

### Using Compiled JAR (Maven)

#### Build the JAR file
```bash
mvn clean package
```

#### Run the JAR
```bash
java -jar target/invoice-generator-1.0-SNAPSHOT.jar
```

#### Run with sample data
```bash
java -jar target/invoice-generator-1.0-SNAPSHOT.jar --sample
```

### All Available Scripts (Windows)

- `run-simple.bat` - Text-only interactive mode (no Maven)
- `run-sample-simple.bat` - Text-only sample invoice (no Maven)
- `run.bat` - Full featured interactive mode (requires Maven)
- `run-sample.bat` - Full featured sample invoice (requires Maven)

## Usage Guide

### Interactive Mode

When you run the application in interactive mode, you'll be prompted to enter:

1. **Invoice Information**:
   - Invoice number
   - Invoice date (or use today's date)

2. **Company Information**:
   - Company name
   - Address details
   - Contact information
   - Website and Tax ID (optional)

3. **Customer Information**:
   - Customer name
   - Address details
   - Contact information

4. **Invoice Items**:
   - Description
   - Quantity
   - Unit price
   - (Add multiple items, press Enter with empty description to finish)

5. **Additional Settings**:
   - Tax rate (default: 10%)
   - Notes (optional)

6. **Output Options**:
   - Choose format: Text, Excel, or Both
   - Specify output directory

### Sample Mode

Running with `--sample` generates a pre-configured invoice with sample data, demonstrating all features of the application.

## Output Files

The application generates invoices with timestamps in the filename:

- **Text Format**: `invoice_[number]_[timestamp].txt`
- **Excel Format**: `invoice_[number]_[timestamp].xlsx`
- **Sample Files**: `sample_invoice_[timestamp].txt/xlsx`

## Invoice Template Features

### Text Format (.txt)
- Clean ASCII layout
- Properly aligned columns
- Professional header and footer
- Detailed item breakdown
- Tax calculations

### Excel Format (.xlsx)
- Professional spreadsheet layout
- Formatted cells with borders
- Currency formatting
- Styled headers
- Proper column sizing

## Example Invoice Content

Both formats include:
- Company letterhead information
- Customer billing details
- Itemized list of products/services
- Subtotal calculations
- Tax calculations
- Final total
- Payment terms
- Optional notes

## Customization

You can easily customize:

1. **Tax Rates**: Modify the default tax rate or enter custom rates
2. **Invoice Templates**: Edit the generator classes to change layout
3. **Company Defaults**: Modify `SampleDataGenerator.java` for default company info
4. **Output Formats**: Extend `InvoiceGenerator` interface for new formats

## Error Handling

The application includes robust error handling for:
- Invalid date formats
- Invalid numeric inputs
- File I/O operations
- Missing directories (automatically created)

## Development

### Adding New Features

1. **New Output Formats**: Implement the `InvoiceGenerator` interface
2. **New Input Methods**: Create classes similar to `ConsoleInputHandler`
3. **Additional Fields**: Extend the model classes as needed

### Testing

Run the test suite:
```bash
mvn test
```

## License

This project is open source and available under the MIT License.

## Support

For issues or questions, please refer to the source code documentation or create an issue in the project repository.
