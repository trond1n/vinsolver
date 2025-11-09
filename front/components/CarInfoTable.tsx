// components/CarInfoTable.tsx
type CarInfoProps = {
    vin: string;
    marka: string;
    model: string;
    nadwozie: string;
    bezpieczenstwo?: string | null;
    generacja: string;
    silnik?: string | null;
    rok_produkcji: number;
  };
  
  export default function CarInfoTable(props: CarInfoProps) {
    return (
      <table className="table-auto border-collapse border border-gray-400 mx-auto mt-8">
        <tbody>
          <tr><td className="border p-2 font-bold">VIN</td><td className="border p-2">{props.vin}</td></tr>
          <tr><td className="border p-2 font-bold">Marka</td><td className="border p-2">{props.marka}</td></tr>
          <tr><td className="border p-2 font-bold">Model</td><td className="border p-2">{props.model}</td></tr>
          <tr><td className="border p-2 font-bold">Nadwozie</td><td className="border p-2">{props.nadwozie}</td></tr>
          <tr><td className="border p-2 font-bold">Generacja</td><td className="border p-2">{props.generacja}</td></tr>
          <tr><td className="border p-2 font-bold">Silnik</td><td className="border p-2">{props.silnik ?? 'Brak danych'}</td></tr>
          <tr><td className="border p-2 font-bold">Bezpieczeństwo</td><td className="border p-2">{props.bezpieczenstwo ?? 'Brak danych'}</td></tr>
          <tr><td className="border p-2 font-bold">Rok produkcji</td><td className="border p-2">{props.rok_produkcji}</td></tr>
        </tbody>
      </table>
    );
  }
  