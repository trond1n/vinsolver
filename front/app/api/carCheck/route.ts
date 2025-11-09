import { NextResponse } from "next/server";

export async function POST(req: Request) {
  try {
    const body = await req.json();

    // proxy do backendu
    const res = await fetch("http://localhost:8080/carCheck", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(body),
    });

    const text = await res.text();

    try {
      const data = JSON.parse(text);
      return NextResponse.json(data);
    } catch {
      console.error("❌ Backend zwrócił HTML zamiast JSON:", text);
      return NextResponse.json(
        { error: "Invalid response from backend" },
        { status: 500 }
      );
    }
  } catch (error) {
    console.error("❌ Proxy error:", error);
    return NextResponse.json(
      { error: "Failed to connect to backend" },
      { status: 500 }
    );
  }
}

// opcjonalnie obsługa GET — np. dla testów w przeglądarce
export async function GET() {
  return NextResponse.json({ message: "CarCheck API działa 🚗" });
}
