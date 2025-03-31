# GitHub Repositories API

Recruitment project developed as part of the recruitment process for Atipera.

## 📌 Task Description

This API allows fetching non-fork GitHub repositories of a given user, including a list of branches in each repository and the SHA of the last commit on each branch.

### Endpoint

`GET http://localhost:8080/repository/{username}`

### Example Response

```json
[
  {
    "repositoryName": "AtiperaInternTask",
    "ownerLogin": "Mredosz",
    "branches": [
      {
        "name": "main",
        "sha": "dcc959493d6aa78bc3629202dee9f82ad17a8173"
      }
    ]
  }
]
