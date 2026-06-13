#!/bin/bash
# Download ASR models for XR-Transcript
# Run this once before building: ./download-models.sh

set -e

ASSETS_DIR="models/src/main/assets"
mkdir -p "$ASSETS_DIR"

# Silero VAD model (628KB)
echo "Downloading Silero VAD model..."
curl -L -o "$ASSETS_DIR/silero_vad.onnx" \
  "https://github.com/k2-fsa/sherpa-onnx/releases/download/asr-models/silero_vad.onnx"

# Whisper small.en int8 model (359MB)
echo "Downloading Whisper small.en model..."
curl -L -o "/tmp/sherpa-whisper-small.en.tar.bz2" \
  "https://github.com/k2-fsa/sherpa-onnx/releases/download/asr-models/sherpa-onnx-whisper-small.en.tar.bz2"

echo "Extracting model..."
tar xjf "/tmp/sherpa-whisper-small.en.tar.bz2" -C "$ASSETS_DIR"

# Keep only int8 quantized models
rm -f "$ASSETS_DIR/sherpa-onnx-whisper-small.en/small.en-decoder.onnx"
rm -f "$ASSETS_DIR/sherpa-onnx-whisper-small.en/small.en-encoder.onnx"
rm -rf "$ASSETS_DIR/sherpa-onnx-whisper-small.en/test_wavs"
rm -f "/tmp/sherpa-whisper-small.en.tar.bz2"

echo "Done. Models at:"
ls -lh "$ASSETS_DIR/silero_vad.onnx"
ls -lh "$ASSETS_DIR/sherpa-onnx-whisper-small.en/"
