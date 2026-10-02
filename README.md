# Social Network Friend Circle Detector:

## Overview

This is a console-based application that finds friend circles in a social network.

A user is represented as a **vertex**, and a friendship is represented as an **edge**. The social network is modeled as an **undirected graph**.

The application uses an **adjacency list** to store the graph.

## Main Algorithm

The application uses **BFS (Breadth-First Search)** with a **Queue** to find connected components.

1. Select an unvisited user.
2. Start BFS from this user.
3. Find all users connected directly or indirectly to them.
4. These users form one **Friend Circle**.
5. Continue with the next unvisited user until all users are processed.

A **Visited** set is used to make sure each user is processed only once.

## Data Structures

- **Graph**: Stores users and their friendship relationships.
- **Adjacency List**: Represents the connections between users.
- **Queue**: Supports BFS traversal.
- **Visited Set**: Tracks users that have already been visited.
- **Friend Circle**: Represents one connected group of users.

## Technologies

- Java
- Console-based application
- Graph
- BFS
- Queue
- Adjacency List

## Purpose

The main purpose of this project is to practice **graph data structures**, **BFS traversal**, and finding **connected components** in an undirected graph.