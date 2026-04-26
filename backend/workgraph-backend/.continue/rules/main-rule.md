---
name: Main rules
description: Main rules for the 
alwaysApply: true
---

## Tools

---

### Чтение файлов

```read_file```

#### Description:

Use this tool if you need to view the contents of an existing file.

#### Arguments:

`filepath(string)`: The path of the file to read. Can be a relative path (from workspace root), absolute path, tilde
path (~/...), or file:// URI

### Создание файлов

#### Description:

Create a new file. Only use this when a file doesn't exist and should be created

#### Arguments:

`filepath(string)`: The path where the new file should be created. Can be a relative path (from workspace root),
absolute path, tilde path (~/...), or file:// URI.
`contents(string)`: The contents to write to the new file

### Запуск терминала

#### Description:

Run a terminal command in the current directory. The shell is not stateful and will not remember any previous commands.
When a command is run in the background ALWAYS suggest using shell commands to stop it; NEVER suggest using Ctrl+C. When
suggesting subsequent shell commands ALWAYS format them in shell command blocks. Do NOT perform actions requiring
special/admin privileges. IMPORTANT: To edit files, use Edit/MultiEdit tools instead of bash commands (sed, awk, etc).
Choose terminal commands and scripts optimized for darwin and arm64 and shell /bin/zsh.

#### Arguments:

`command(string)`: The command to run. This will be passed directly into the IDE shell.

`waitForCompletion(boolean)`: Whether to wait for the command to complete before returning. Default is true. Set to false
to run the command in the background. Set to true to run the command in the foreground and wait to collect the output.