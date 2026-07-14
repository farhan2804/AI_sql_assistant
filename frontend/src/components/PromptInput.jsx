import { useState } from "react";
import api from "../services/api";

function PromptInput() {
  const [prompt, setPrompt] = useState("");
  const [sql, setSql] = useState("");
  const [loading, setLoading] = useState(false);
  const generateSql = async () => {
    if (!prompt.trim()) {
      alert("Please enter a prompt.");
      return;
    }
    setLoading(true);
    try {
      const response = await api.post("/sql/generate", {
        prompt: prompt,
      });
      setSql(response.data.sql);
    } catch (error) {
      alert("Failed to generate SQL");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };
  const copySql = async () => {
    try {
      await navigator.clipboard.writeText(sql);
      alert("SQL copied to clipboard!");
    } catch (error) {
      alert("Failed to copy SQL.");
    }
  };
  return (
    <div
      style={{
        maxWidth: "800px",
        margin: "40px auto",
        display: "flex",
        flexDirection: "column",
        gap: "20px",
      }}
    >
      <h2>Describe your SQL query</h2>

      <textarea
        rows="6"
        value={prompt}
        onChange={(e) => setPrompt(e.target.value)}
        placeholder="Example: Show all employees earning more than 50000"
        style={{
          padding: "15px",
          fontSize: "16px",
          resize: "none",
        }}
      />

      <button
        onClick={generateSql}
        style={{
          padding: "15px",
          fontSize: "18px",
          cursor: "pointer",
        }}
      >
        {loading ? "Generating..." : "Generate SQL"}
      </button>

      {sql && (
        <>
          <h3>Generated SQL</h3>

          <textarea
            rows="5"
            value={sql}
            readOnly
            style={{
              padding: "15px",
              fontSize: "16px",
              background: "#f5f5f5",
            }}
          />

          <button
            onClick={copySql}
            style={{
              padding: "12px",
              fontSize: "16px",
              cursor: "pointer",
            }}
          >
            📋 Copy SQL
          </button>
        </>
      )}
    </div>
  );
}

export default PromptInput;
