import { useEffect, useState } from "react";
import Navbar from "./components/Navbar";
import PromptInput from "./components/PromptInput";

function App() {
  const [darkMode, setDarkMode] = useState(() => {
    return localStorage.getItem("theme") === "dark";
  });

  useEffect(() => {
    if (darkMode) {
      document.documentElement.classList.add("dark");
      localStorage.setItem("theme", "dark");
    } else {
      document.documentElement.classList.remove("dark");
      localStorage.setItem("theme", "light");
    }
  }, [darkMode]);

  return (
    <div
      className={
        darkMode
          ? "dark min-h-screen bg-slate-900"
          : "min-h-screen bg-slate-100"
      }
    >
      <Navbar darkMode={darkMode} setDarkMode={setDarkMode} />
      <PromptInput darkMode={darkMode} />
    </div>
  );
}

export default App;
