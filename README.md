# Flight Booking API

A minimal REST API for booking flight tickets, built with Spring Boot and Java 17.

## How to Run

```bash
mvn spring-boot:run
```

The service starts on `http://localhost:8080`.

## Example Requests

### Create a flight

```bash
curl -X POST http://localhost:8080/flights \
  -H "Content-Type: application/json" \
  -d '{
    "flightNumber": "AC123",
    "origin": "Toronto",
    "destination": "Montreal",
    "departureTime": "2026-07-01T10:00:00",
    "totalSeats": 2
  }'
```

### Book a seat

```bash
curl -X POST http://localhost:8080/flights/AC123/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "passengerName": "Charles Fakih",
    "passengerEmail": "charles@example.com"
  }'
```

### Trigger overbooking error (after seats are full)

```bash
curl -X POST http://localhost:8080/flights/AC123/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "passengerName": "Jane Doe",
    "passengerEmail": "jane@example.com"
  }'
```

Returns `409 Conflict` when the flight is fully booked.

## What I Would Improve With More Time

- **Cancellation endpoints:** Currently there's no way to cancel a booking. Seats are just consumed. I would add a DELETE /bookings/{reference}

- **Idempotency:** A client retrying a failed request could create a duplicate booking. I would cache recent responses to make bookings safe to retry.

- **Persistent storage:** I would change the in-memory store for a DB like Postgres (easily compatible with Java). With our decoupled service layer this is not a steep change.

- **Concurrency tests:** I would add integration tests that would test race conditions especially in the overbooking scenario.

- **Structured logging:** I would add logging for HTTP requests and responses for better traceability.