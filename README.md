# Retrospekt - An LLM Powered Content Resurfacing Application

Uses the capabilities of LLMs to summarize and categorize online saved content.\
Also recommends links to similar websites.\
View saved content on dashboard and get\
weekly newsletters via mail to read saved content to read everything at once.\
*Currently Supports Twitter/X.

Libraries/Frameworks used: Spring Boot, Langchain4j


## Pre-requisites
Docker, Node.js

## Extension
Enable developer mode on the browser and add the extension llmAppExtension using "Load Unpacked"

## Running the Spring Boot service
1. Create Database directory in your home folder: mkdir ~/Database
2. Navigate to the directory "backend". Open the Dockerfile, and
set the environment variable USER_NAME to the email address that should receive the weekly newsletters.
3. Run the Spring Boot service: docker-compose up --build
4. Save content on twitter using the extension button provided


## Viewing the dashboard
1. Navigate to the "frontend" directory and run: npm install --> npm run dev