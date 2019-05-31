rem **Builds and runs the arduino IDE for testing Jacob Smith
rem reference 1 https://superuser.com/questions/757146/windows-7-cmd-command-to-keep-cmd-open-after-executing
rem reference 2 https://www.wikihow.com/Delete-a-File-Using-Command-Prompt
rem reference 3 https://superuser.com/questions/1189975/how-can-i-force-stop-a-program-without-using-the-mouse-in-windows-10
rem reference 4 https://superuser.com/questions/1179510/wait-for-a-process-to-complete-in-cmd
rem reference 5 https://www.tutorialspoint.com/batch_script/batch_script_if_statement.htm
rem reference 6 https://stackoverflow.com/questions/734598/how-do-i-make-a-batch-file-terminate-upon-encountering-an-error
rem reference 7 https://superuser.com/questions/101974/play-a-sound-maybe-wav-from-windows-line-command/528541

rem temp, sets copmuter to shutdown so I have to take a break
shutdown /s /t 1200
rem **delete file that results in compilation error ref 3
cd C:\Users\jsmit\Downloads\Arduino\build\windows\work\lib
taskkill /F /IM "javaw.exe" /T
rem delte the file, see ref 2
del jnidispatch-4.2.2-win32-x86.dll
rem  go to directory, compile run the arduino package
cd C:\Users\jsmit\Downloads\Arduino\build
rem see 4 for call, which forces commands to be sequential
call ant build
rem these two lines stop the script and play a sound, see reference 1,4,5,6,7
if %errorlevel% neq 0 pause
if %errorlevel% neq 0 start wmplayer "C:\Windows\Media\Alarm2.wav" && timeout 5 && taskkill /im wmplayer.exe
call ant run
if %errorlevel% neq 0 pause
if %errorlevel% neq 0 start wmplayer "C:\Windows\Media\Alarm2.wav" && timeout 5 && taskkill /im wmplayer.exe