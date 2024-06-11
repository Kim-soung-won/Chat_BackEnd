#!/bin/sh

# JAR 파일 경로 찾기
JAR_FILE=$(ls /work/kotlinApp-*-SNAPSHOT.jar | head -n 1)

# JAR 파일 실행
if [ -n "$JAR_FILE" ]; then
    java -jar "$JAR_FILE"
else
    echo "No JAR file found"
    exit 1
fi
