package com.apagao.service;

import java.util.HashMap;
import java.util.Map;

public class AlertaService {

    public String consultarAlertaPorEstado(String uf) {
        Map<String, String> alertas = new HashMap<>();

        alertas.put("SP", "⚠️ Risco de sobrecarga no sistema elétrico devido à onda de calor.");
        alertas.put("RJ", "🌩️ Tempestade prevista. Possibilidade de cortes de energia em áreas litorâneas.");
        alertas.put("MG", "🔌 Falta de energia intermitente em zonas rurais.");
        alertas.put("BA", "🔥 Alerta de calor extremo. Aumente sua hidratação e evite sobrecarga elétrica.");
        alertas.put("RS", "🌧️ Chuvas fortes previstas. Evite áreas com histórico de alagamento.");

        return alertas.getOrDefault(uf.toUpperCase(), "✅ Nenhum alerta de risco para esta região.");
    }
}
