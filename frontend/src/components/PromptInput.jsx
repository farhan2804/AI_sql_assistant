import { useState } from "react";
import { toast } from "react-toastify";
import api from "../services/api";

function PromptInput({ darkMode }) {
  const [prompt, setPrompt] = useState("");
  const [sql, setSql] = useState("");
  const [data, setData] = useState([]);
  const [loading, setLoading] = useState(false);

  const generateSql = async () => {
    if (!prompt.trim()) {
      toast.warning("Please enter a prompt.");
      return;
    }

    setLoading(true);

    try {
      const response = await api.post("/sql/generate", {
        prompt,
      });

      setSql(response.data.sql);
      setData(response.data.data);

      toast.success("SQL generated successfully!");
    } catch (error) {
      toast.error("Failed to generate SQL.");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const copySql = async () => {
    try {
      await navigator.clipboard.writeText(sql);
      toast.success("SQL copied successfully!");
    } catch {
      toast.error("Failed to copy SQL.");
    }
  };

  const clearAll = () => {
    setPrompt("");
    setSql("");
    setData([]);
    toast.info("Workspace cleared.");
  };

  const handleKeyDown = (e) => {
    if (e.ctrlKey && e.key === "Enter") {
      generateSql();
    }
  };

  return (
    <div className="max-w-7xl mx-auto p-6 mt-8">
      <div
        className={`rounded-2xl shadow-xl p-8 transition-all duration-300 ${
          darkMode ? "bg-slate-800 text-white" : "bg-white text-slate-900"
        }`}
      >
        <h2 className="text-3xl font-bold mb-2">AI SQL Generator</h2>

        <p className={`mb-6 ${darkMode ? "text-slate-300" : "text-slate-500"}`}>
          Describe your query in plain English.
        </p>

        <textarea
          rows="5"
          value={prompt}
          onChange={(e) => setPrompt(e.target.value)}
          onKeyDown={handleKeyDown}
          placeholder="Example: Show all employees earning more than 50000"
          className={`w-full rounded-xl border p-4 text-lg transition-all
          ${
            darkMode
              ? "bg-slate-900 border-slate-700 text-white placeholder:text-slate-400"
              : "bg-gray-50 border-gray-300 text-black"
          }
          focus:outline-none focus:ring-2 focus:ring-blue-500`}
        />

        <div className="flex flex-wrap gap-4 mt-6">
          <button
            onClick={generateSql}
            disabled={loading}
            className="flex-1 bg-blue-700 hover:bg-blue-700 text-white font-semibold py-3 rounded-xl cursor-pointer transition-all duration-200 hover:scale-102 disabled:bg-gray-400 disabled:cursor-not-allowed"
          >
            {loading ? "Generating..." : "Generate SQL"}
          </button>

          <button
            onClick={clearAll}
            className="bg-red-600 hover:bg-red-700 text-white px-6 rounded-xl cursor-pointer transition-all duration-200 hover:scale-105"
          >
            🗑 Clear
          </button>
        </div>

        <p
          className={`mt-3 text-sm ${
            darkMode ? "text-slate-400" : "text-gray-500"
          }`}
        >
          💡 Tip: Press <strong>Ctrl + Enter</strong> to generate SQL instantly.
        </p>

        {sql && (
          <div className="mt-10">
            <div className="flex justify-between items-center mb-4">
              <h3 className="text-2xl font-semibold">Generated SQL</h3>

              <button
                onClick={copySql}
                className="bg-green-600 hover:bg-emerald-700 text-white px-5 py-2 rounded-xl cursor-pointer transition-all duration-300 hover:scale-110 hover:shadow-xl active:scale-95"
              >
                📋 Copy SQL
              </button>
            </div>

            <textarea
              value={sql}
              readOnly
              rows="4"
              className={`w-full rounded-xl p-4 font-mono
              ${
                darkMode
                  ? "bg-slate-900 text-green-400"
                  : "bg-gray-100 text-green-700"
              }`}
            />
          </div>
        )}

        {data.length > 0 && (
          <div className="mt-10">
            <h3 className="text-2xl font-semibold mb-4">Query Results</h3>

            <div className="overflow-x-auto rounded-xl">
              <table className="min-w-full">
                <thead>
                  <tr
                    className={
                      darkMode
                        ? "bg-slate-900 text-white"
                        : "bg-slate-800 text-white"
                    }
                  >
                    {Object.keys(data[0]).map((key) => (
                      <th key={key} className="px-5 py-4 text-left">
                        {key.toUpperCase()}
                      </th>
                    ))}
                  </tr>
                </thead>

                <tbody>
                  {data.map((row, index) => (
                    <tr
                      key={index}
                      className={`border-b transition hover:bg-blue-50
                      ${
                        darkMode
                          ? "border-slate-700 hover:bg-slate-700"
                          : "border-gray-200"
                      }`}
                    >
                      {Object.values(row).map((value, i) => (
                        <td key={i} className="px-5 py-4">
                          {String(value)}
                        </td>
                      ))}
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        )}
      </div>
    </div>
  );
}

export default PromptInput;
