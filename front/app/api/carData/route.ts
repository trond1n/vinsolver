import { NextResponse } from "next/server";

export async function GET() {
  try {
    const res = await fetch("http://localhost:8080/carData");
    const text = await res.text();

    if (!text) {
      return NextResponse.json(
        { error: "Backend zwrócił pustą odpowiedź" },
        { status: 500 }
      );
    }

    try {
      // próbujemy sparsować JSON
      const data = JSON.parse(text);
      return NextResponse.json(data);
    } catch {
      // jeśli nie JSON, zwracamy treść backendu w polu "error"
      console.error("❌ Backend zwrócił nie-JSON:", text);
      return NextResponse.json(
        { error: text },
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
