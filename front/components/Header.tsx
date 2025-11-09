import styles from '../styles/Header.module.css';
export default function Header() {
  return (
    <header className={styles.header}>
      <h1 className={styles.logo}>
        <span className={styles.orange}>vin</span>
        <span className={styles.blue}>solver</span>
      </h1>
      <nav className={styles.nav}>
        <a href="#">Features</a>
        <a href="#">How it Works</a>
        <a href="#">FAQ</a>
      </nav>
    </header>
  );
}60