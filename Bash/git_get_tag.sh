#!/usr/bin/env bash
git name-rev --tags --name-only $(git rev-parse HEAD)