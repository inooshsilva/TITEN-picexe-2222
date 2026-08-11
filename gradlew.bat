@echo off
setlocal
set "GRADLE_VERSION=8.8"
set "CACHE=%USERPROFILE%\.gradle\wrapper\dists\titan-gradle\%GRADLE_VERSION%"
set "DIST=%CACHE%\gradle-%GRADLE_VERSION%"
if exist "%DIST%\bin\gradle.bat" goto run
if not exist "%CACHE%" mkdir "%CACHE%"
powershell -NoProfile -ExecutionPolicy Bypass -Command "(New-Object Net.WebClient).DownloadFile('https://services.gradle.org/distributions/gradle-%GRADLE_VERSION%-bin.zip','%CACHE%\gradle.zip')"
powershell -NoProfile -ExecutionPolicy Bypass -Command "Expand-Archive -Force '%CACHE%\gradle.zip' '%CACHE%'"
del "%CACHE%\gradle.zip"
:run
call "%DIST%\bin\gradle.bat" %*
