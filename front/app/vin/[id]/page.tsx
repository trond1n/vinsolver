import CarInfoTable from '@/components/CarInfoTable';
import Header from '@/components/Header';
import Hero from '@/components/Hero';
import Footer from '@/components/Footer';

type Props = {
  params: {
    id: string;
  };
};

async function getCarData(vin: string) {
  const res = await fetch(`http://localhost:8080/vin/${vin}`, {
    cache: 'no-store',
  });

  if (!res.ok) throw new Error('Nie udało się pobrać danych o samochodzie');
  return res.json();
}

export default async function VinPage({ params }: Props) {
  const car = await getCarData(params.id);

  return (
    <div>
      <Header />
      <Hero />
      <CarInfoTable
        vin={car.vin}
        marka={car.marka}
        model={car.model}
        nadwozie={car.nadwozie}
        generacja={car.generacja}
        silnik={car.silnik}
        bezpieczenstwo={car.bezpieczenstwo}
        rok_produkcji={car.rok_produkcji}
      />
      
    </div>
  );
}
