# AI Usage Documentation

This project used AI assistance (Cursor) in the following areas:

## Usage Context

- Improving exception handling and overall robustness (e.g., clearer error messages, null safety, input validation)

## Specific Contributions

- Added validation for empty input, missing arguments, invalid date formats, and illegal indices in `lars/parser/Parser.java`, with clearer error messages.
- Added index boundary checks in `lars/command/MarkCommand.java` to prevent illegal access.
- Added capacity checks in `lars/command/AddCommand.java` to prevent array overflow and provide clearer error feedback when the task list is full.
- Added save-file format validation and capacity limits in `lars/storage/Storage.java`, with meaningful error messages when corrupted data is encountered.
- Ensured `commandType` is properly set during exceptions in `lars/Lars.java` to prevent potential UI null pointer issues.
- Adjusted exit condition handling in `MainWindow.java` to prevent potential null pointer exceptions.

## Declaration

All AI-generated suggestions were carefully reviewed, tested, and validated before being integrated into the project. The final implementation reflects my own understanding and complies with course requirements.
