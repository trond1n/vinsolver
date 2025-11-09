import styles from '../styles/HowItWorks.module.css';

export default function HowItWorks() {
  return (
    <section className={styles.howItWorks}>
      <h3 className="text-5xl">How It Works</h3>
      <div className={styles.steps}>
        <div>
          <div>🔍</div>
          <p>Enter the vehicle’s VIN number</p>
        </div>
        <div>
          <div>📝</div>
          <p>We analyze vehicle’s history</p>
        </div>
        <div>
          <div>📊</div>
          <p>Receive a comprehensive report</p>
        </div>
      </div>
      <div className={styles.buttonWrapper}>
        <button>Check History</button>
      </div>
    </section>
  );
}
