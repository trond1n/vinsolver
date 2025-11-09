import styles from '../styles/Advantages.module.css';

const advantages = [
  { title: 'Detailed Reports', desc: 'Extensive information on vehicle’s history' },
  { title: 'Reliable Data', desc: 'Accurate and up-to-date data sources' },
  { title: 'Fast Results', desc: 'Quick access to vehicle history' },
  { title: 'Easy-to-Use', desc: 'Simple interface for seamless experience' },
];

export default function Advantages() {
  return (
    <section className={styles.advantages}>
      <h5 className="text-5xl">Our Advantages</h5>
      <div className={styles.grid}>
        {advantages.map((adv, i) => (
          <div key={i} className={styles.card}>
            <h4>{adv.title}</h4>
            <p>{adv.desc}</p>
          </div>
        ))}
      </div>
    </section>
  );
}