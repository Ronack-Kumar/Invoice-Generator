# Invoice Generator Web UI

A React interface for entering invoice details, previewing the result, and downloading PDF or Excel files. This frontend is independent of the repository's Java command-line application.

## Features

- Company and customer details, invoice number/date, line items, tax rate, and notes.
- Automatic subtotal, tax, and total calculations.
- A live invoice preview alongside the input form.
- PDF export through `html2pdf.js`, using an A4 portrait configuration.
- Excel export through SheetJS `xlsx`, including the invoice details, items, and totals.

## Stack

React 19, Vite 8, `html2pdf.js`, SheetJS `xlsx`, and Lucide React icons. The source is JavaScript/JSX.

## Run locally

Use a Node.js version supported by Vite 8. From this directory:

```bash
npm install
npm run dev
```

Open the URL printed by Vite. A Java server is not required.

## Scripts

| Command | Purpose |
| --- | --- |
| `npm run dev` | Start the Vite development server |
| `npm run build` | Create the frontend production build |
| `npm run preview` | Preview an existing production build |
| `npm run lint` | Run ESLint |

## Source layout

- [`src/App.jsx`](src/App.jsx): invoice state, calculation functions, and PDF/Excel export handlers.
- [`src/components/InvoiceForm.jsx`](src/components/InvoiceForm.jsx): invoice inputs and line-item controls.
- [`src/components/InvoicePreview.jsx`](src/components/InvoicePreview.jsx): invoice rendering used by the PDF exporter.
- [`vite.config.js`](vite.config.js): Vite configuration.

The Java CLI uses Apache POI for Excel output; this web application uses browser-side libraries. See the [repository README](../README.md) for the CLI setup.
