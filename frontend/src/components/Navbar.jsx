function Navbar({ darkMode, setDarkMode }) {
  return (
    <nav
      className={`shadow-md transition-colors duration-300 ${
        darkMode
          ? "bg-slate-950 text-white"
          : "bg-slate-900 text-white"
      }`}
    >
      <div className="max-w-6xl mx-auto px-6 py-4 flex items-center justify-between">

        <div>
          <h1 className="text-3xl font-bold">
            🤖 AI SQL Assistant
          </h1>

          <p className="text-sm text-slate-300 mt-1">
            Convert Natural Language into SQL using AI
          </p>
        </div>

        <button
          onClick={() => setDarkMode(!darkMode)}
          className="bg-slate-700 hover:bg-slate-600 text-white px-4 py-2 rounded-lg cursor-pointer transition-all duration-200 hover:scale-105"
        >
          {darkMode ? "☀️ Light" : "🌙 Dark"}
        </button>

      </div>
    </nav>
  );
}

export default Navbar;