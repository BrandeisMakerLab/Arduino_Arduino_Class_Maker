rem **Builds and runs the arduino IDE for testing Jacob Smith
rem reference https://superuser.com/questions/757146/windows-7-cmd-command-to-keep-cmd-open-after-executing
rem reference https://www.wikihow.com/Delete-a-File-Using-Command-Prompt
rem reference https://superuser.com/questions/1189975/how-can-i-force-stop-a-program-without-using-the-mouse-in-windows-10
rem reference https://superuser.com/questions/1179510/wait-for-a-process-to-complete-in-cmd
rem **delete file that results in compilation error
cd C:\Users\jsmit\Downloads\Arduino\build\windows\work\lib
taskkill /F /IM "javaw.exe" /T
del jnidispatch-4.2.2-win32-x86.dll
rem  go to directory, compile run the arduino package
cd C:\Users\jsmit\Downloads\Arduino\build
call ant build
call ant run
