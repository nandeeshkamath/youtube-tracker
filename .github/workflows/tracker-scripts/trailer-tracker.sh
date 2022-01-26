#!/bin/sh

# Youtube Channel IDs

NETFLIX=UCWOA1ZGywLbqmigxE4Qlvuw
NETFLIX_INDIA=UCZSNzBgFub_WWil6TOTYwAg
NETFLIX_ANIME=UCBSs9x2KzSLhyyA9IKyt4YA
SWOON=UCpiCK8c6PBktcxq7Az_t4RQ
PRIME=UCQJWtTnAHhEG5w4uN0udnUQ
PRIME_INDIA=UCWy7vmlB3BoVq4rqF2dc5nQ
HOTSTAR=UC0PTktRYpZXb6On0_zFKWIg
TSERIES=UCq-Fj5jknLsUf-MWSy4_brA
WBBROS=UCjmJDM5pRKbUlVIzDYYWb6g
SONYLIV=UCOQNJjhXwvAScuELTT_i7cQ
ZEE_MUSIC=UCFFbwnve3yF62-tVXkTyHqg

curl --location --request POST "$DOMAIN_URL/track" \
--header "Authorization: Basic $SECRET_TOKEN" \
--header 'Content-Type: application/json' \
--data-raw "{
    \"keywords\": [
      \"trailer\",
      \"teaser\",
      \"first look\"
    ],
    \"interval\": 4,
    \"channels\": [
        \"$NETFLIX\",
        \"$NETFLIX_INDIA\",
        \"$NETFLIX_ANIME\",
        \"$SWOON\",
        \"$PRIME\",
        \"$PRIME_INDIA\",
        \"$HOTSTAR\",
        \"$TSERIES\",
        \"$WBBROS\",
        \"$SONYLIV\",
        \"$ZEE_MUSIC\"
    ]
}"
