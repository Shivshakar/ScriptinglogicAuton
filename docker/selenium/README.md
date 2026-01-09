Selenium MCP Server (Docker) — Quick Start

This folder contains a docker-compose setup to run a Selenium standalone (Chrome) server for local testing/demo.

Files included
- `docker-compose.yml` — starts a Selenium standalone Chrome container (Selenium 4).

Quick usage
1. Start the Selenium server

```bash
cd docker/selenium
docker-compose up -d
```

2. Confirm the server is running (default URL):

```bash
# Selenium 4 WebDriver endpoint
http://localhost:4444/
```

3. Configure the project to use the grid
- In `config/config.properties`, set `grid.url = http://localhost:4444/`
- The project will automatically use the RemoteWebDriver when `grid.url` is present.

4. Run tests (they will run against the Selenium server):

```bash
# from project root
mvn -Dtest=regression.LoginTest test
```

Stop the Selenium server

```bash
cd docker/selenium
docker-compose down
```

Notes
- The compose uses the official Selenium images. Adjust versions in `docker-compose.yml` if you need different browsers or nodes.
- For CI, consider using Selenium Grid in Docker or cloud providers (Sauce Labs, BrowserStack).
