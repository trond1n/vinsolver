'use client';

import { useState, useEffect } from "react";

interface Category {
  zalety: string[];
  wady: string[];
}

interface CarResponse {
  categories: {
    [category: string]: Category;
  };
}

export default function CarCheckPage() {
  const [carData, setCarData] = useState<{ [brand: string]: { [model: string]: string[] } }>({});
  const [brand, setBrand] = useState("");
  const [model, setModel] = useState("");
  const [engine, setEngine] = useState("");
  const [transmission, setTransmission] = useState("");
  const [year, setYear] = useState<number | "">("");
  const [mileage, setMileage] = useState<number | "">("");
  const [responseData, setResponseData] = useState<CarResponse | null>(null);
  const [loading, setLoading] = useState(false);
  const [expandedCategories, setExpandedCategories] = useState<{ [key: string]: boolean }>({});

  // Pobranie danych o samochodach z backendu
  useEffect(() => {
    fetch("/api/carData")
      .then((res) => res.json())
      .then((data) => setCarData(data))
      .catch((err) => console.error("❌ Błąd ładowania carData:", err));
  }, []);

  const handleSubmit = async () => {
    if (!brand || !model || !engine || !transmission || !year || !mileage) {
      alert("Wypełnij wszystkie pola!");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("/api/carCheck", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ brand, model, engine, transmission, year, mileage }),
      });

      const data = await res.json();
      setResponseData(data);

      const initialExpanded: { [key: string]: boolean } = {};
      Object.keys(data.categories).forEach((cat) => (initialExpanded[cat] = false));
      setExpandedCategories(initialExpanded);
    } catch (error) {
      console.error("❌ Błąd:", error);
      alert("Nie udało się pobrać danych z serwera.");
    } finally {
      setLoading(false);
    }
  };

  const toggleCategory = (category: string) => {
    setExpandedCategories((prev) => ({ ...prev, [category]: !prev[category] }));
  };

  return (
    <div style={{ maxWidth: 700, margin: "50px auto", padding: 20 }}>
      <div
        style={{
          marginBottom: 40,
          padding: 20,
          border: "1px solid #ccc",
          borderRadius: 10,
          boxShadow: "0 4px 6px rgba(0,0,0,0.1)",
        }}
      >
        <h2 style={{ textAlign: "center", marginBottom: 20 }}>Sprawdź samochód</h2>
        <div style={{ display: "flex", flexDirection: "column", gap: 15 }}>
          
          {/* Marka */}
          <select
            value={brand}
            onChange={(e) => {
              setBrand(e.target.value);
              setModel("");
              setEngine("");
            }}
            style={{ padding: 10, borderRadius: 5, border: "1px solid #ccc" }}
          >
            <option value="">Wybierz markę</option>
            {Object.keys(carData).map((b) => (
              <option key={b} value={b}>
                {b}
              </option>
            ))}
          </select>

          {/* Model */}
          <select
            value={model}
            onChange={(e) => {
              setModel(e.target.value);
              setEngine("");
            }}
            style={{ padding: 10, borderRadius: 5, border: "1px solid #ccc" }}
            disabled={!brand}
          >
            <option value="">Wybierz model</option>
            {brand &&
              Object.keys(carData[brand] || {}).map((m) => (
                <option key={m} value={m}>
                  {m}
                </option>
              ))}
          </select>

          {/* Silnik */}
          <select
            value={engine}
            onChange={(e) => setEngine(e.target.value)}
            style={{ padding: 10, borderRadius: 5, border: "1px solid #ccc" }}
            disabled={!model}
          >
            <option value="">Wybierz silnik</option>
            {brand && model && (carData[brand]?.[model] || []).map((eng) => (
              <option key={eng} value={eng}>
                {eng}
              </option>
            ))}
          </select>

          {/* Rok */}
          <input
            type="number"
            placeholder="Rok produkcji"
            value={year}
            onChange={(e) => setYear(Number(e.target.value))}
            style={{ padding: 10, borderRadius: 5, border: "1px solid #ccc" }}
          />

          {/* Przebieg */}
          <input
            type="number"
            placeholder="Przebieg (km)"
            value={mileage}
            onChange={(e) => setMileage(Number(e.target.value))}
            style={{ padding: 10, borderRadius: 5, border: "1px solid #ccc" }}
          />

          {/* Skrzynia biegów */}
          <select
            value={transmission}
            onChange={(e) => setTransmission(e.target.value)}
            style={{ padding: 10, borderRadius: 5, border: "1px solid #ccc" }}
          >
            <option value="">Wybierz skrzynię biegów</option>
            <option value="manualna">Manualna</option>
            <option value="automatyczna">Automatyczna</option>
          </select>

          <button
            onClick={handleSubmit}
            style={{
              padding: 12,
              borderRadius: 5,
              border: "none",
              backgroundColor: "#0070f3",
              color: "white",
              fontWeight: "bold",
              cursor: "pointer",
            }}
          >
            {loading ? "Ładowanie..." : "Sprawdź"}
          </button>
        </div>
      </div>

      {/* Wynik */}
      {responseData?.categories && (
        <div style={{ display: "flex", flexDirection: "column", gap: 15 }}>
          {Object.entries(responseData.categories).map(([cat, details]) => (
            <div
              key={cat}
              style={{
                padding: 15,
                border: "1px solid #0070f3",
                borderRadius: 10,
                boxShadow: "0 2px 4px rgba(0,0,0,0.1)",
              }}
            >
              <div
                onClick={() => toggleCategory(cat)}
                style={{ display: "flex", justifyContent: "space-between", cursor: "pointer", alignItems: "center" }}
              >
                <h3 style={{ margin: 0 }}>{cat}</h3>
                <span style={{ fontSize: 24 }}>{expandedCategories[cat] ? "−" : "+"}</span>
              </div>

              {expandedCategories[cat] && (
                <div style={{ marginTop: 10 }}>
                  <div>
                    <strong>Zalety:</strong>
                    <ul>{details?.zalety?.map((i, idx) => <li key={idx}>{i}</li>)}</ul>
                  </div>
                  <div>
                    <strong>Wady:</strong>
                    <ul>{details?.wady?.map((i, idx) => <li key={idx}>{i}</li>)}</ul>
                  </div>
                </div>
              )}
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
