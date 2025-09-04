@echo off
setlocal
set HOST=0.0.0.0
set PORT=8001

rem Prefer Python launcher if available; fallback to python
py -m uvicorn app.main:app --host %HOST% --port %PORT% --reload || python -m uvicorn app.main:app --host %HOST% --port %PORT% --reload
