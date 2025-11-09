#!/bin/bash

set -euo pipefail

cd "/Users/tr0nd1n/Downloads/Telegram Desktop/den-workspace/front"
corepack enable
if [ -n "${NODE_OPTIONS-}" ]; then
  unset NODE_OPTIONS
fi
npm run build

