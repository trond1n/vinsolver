## vinsolver

A lightweight full-stack platform for validating and decoding Vehicle Identification Numbers (VINs). The backend exposes REST endpoints for VIN decoding, while the frontend delivers a user-facing Next.js application.

### Repository Structure

- `back/` — Spring Boot service that performs VIN validation and decoding.
- `front/` — Next.js frontend that consumes the VIN API and renders the UI.
- `scripts/` — Helper scripts for building and running both services.

### Tech Stack

- Backend: Java 8, Spring Boot 2.3, Spring Web, Spring Data JPA, Log4j.
- Frontend: Next.js 15, React 19, TypeScript, Tailwind CSS 4.
- Tooling: Maven 3.8+, npm 10+, ESLint, Prettier.
- Data: In-memory JSON data with optional connectors for H2/MySQL.

### Prerequisites

- Node.js 20+ and npm 10+.
- JDK 8 or newer.
- Maven 3.8 or newer.
- (Optional) Docker 24+ if you plan to containerize the services.

### Getting Started

1. **Install dependencies**
   ```bash
   cd back && mvn clean install
   cd ../front && corepack enable && npm install
   ```
   Alternatively use the helper scripts `./scripts/build-back.sh` and `./scripts/build-front.sh`.
2. **Configure environment**
   - Backend configuration lives in `back/src/main/resources/application.yml` and `application.properties`. Adjust database settings or external API credentials there.
   - Frontend supports a `.env.local` file under `front/` for API endpoints or feature flags.
3. **Run the services**
   - Backend: `cd back && mvn spring-boot:run` or `./scripts/start-back.sh` (exposes REST API at `http://localhost:8080`).
   - Frontend: `cd front && npm run dev` or `./scripts/start-front.sh` (serves UI at `http://localhost:3000`).
4. **Quality checks**
   - Backend tests: `cd back && mvn test`.
   - Frontend linting: `cd front && npm run lint`.

### Production Builds

- Backend: `cd back && mvn -DskipTests clean package` (outputs a WAR file in `back/target`).
- Frontend: `cd front && npm run build` followed by `npm run start` to serve the optimized build.

### Additional Notes

- Update `back/src/main/java/com/dominik/klientbackend/service/VinSolver.java` if you integrate third-party VIN decoding providers.
- Serve the frontend behind a reverse proxy that forwards API calls to the Spring Boot service for production deployments.
