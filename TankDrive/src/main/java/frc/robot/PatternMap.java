// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

import java.util.HashMap;

public class PatternMap {
  /** Creates a new VisualFeedbackSubsystem. */
  public HashMap<String,Double> m_colorMap = new HashMap<String,Double>();
  public PatternMap()
  {
    m_colorMap.put("hot pink", 0.57);
    m_colorMap.put("dark red", 0.59);
    m_colorMap.put("red", 0.61);
    m_colorMap.put("red orange", 0.63);
    m_colorMap.put("orange", 0.65);
    m_colorMap.put("gold", 0.67);
    m_colorMap.put("yellow", 0.69);
    m_colorMap.put("lawn green", 0.71);
    m_colorMap.put("lime", 0.73);
    m_colorMap.put("dark green", 0.75);
    m_colorMap.put("green", 0.77);
    m_colorMap.put("blue green", 0.79);
    m_colorMap.put("aqua", 0.81);
    m_colorMap.put("sky blue", 0.83);
    m_colorMap.put("dark blue", 0.85);
    m_colorMap.put("blue", 0.87);
    m_colorMap.put("blue violet", 0.89);
    m_colorMap.put("violet", 0.91);
    m_colorMap.put("white", 0.93);
    m_colorMap.put("grey", 0.95);
    m_colorMap.put("dark grey", 0.97);
    m_colorMap.put("black", 0.99);
    m_colorMap.put("twinkles", 0.51);
    m_colorMap.put("strobe", 0.35);
    m_colorMap.put("confetti", -0.87);
    m_colorMap.put("rainbow party palette", -0.97);
  }
  public double getPattern(String color)
  {
    return m_colorMap.get(color);
  }
    // m_pattern.setString(defaultColor);
}