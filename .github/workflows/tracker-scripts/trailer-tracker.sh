#!/bin/sh

# Youtube Channel IDs

NETFLIX=UCWOA1ZGywLbqmigxE4Qlvuw
NETFLIX_INDIA=UCZSNzBgFub_WWil6TOTYwAg
NETFLIX_ANIME=UCBSs9x2KzSLhyyA9IKyt4YA
SWOON=UCpiCK8c6PBktcxq7Az_t4RQ

PRIME=UCQJWtTnAHhEG5w4uN0udnUQ
PRIME_INDIA=UC4zWG9LccdWGUlF77LZ8toA

HOTSTAR=UC0PTktRYpZXb6On0_zFKWIg
TSERIES=UCq-Fj5jknLsUf-MWSy4_brA
SONYLIV=UCOQNJjhXwvAScuELTT_i7cQ
ZEE_MUSIC=UCFFbwnve3yF62-tVXkTyHqg
AHA=UCmO-jDLU-KUcweCzktuDsbg
SONY_SOUTH=UCn4rEMqKtwBQ6-oEwbd4PcA

MARVEL=UCvC4D8onUfXzvjTOM-dBfEA
WBBROS=UCjmJDM5pRKbUlVIzDYYWb6g
SONY_PICTURES=UCz97F7dMxBNOfGYu3rx8aCw
KINOCHECK=UCLRlryMfL8ffxzrtqv0_k_w

curl --location --request POST "$DOMAIN_URL/track" \
--header "Authorization: Basic $SECRET_TOKEN" \
--header 'Content-Type: application/json' \
--data-raw "{
    \"keywords\": [
      \"trailer\",
      \"teaser\"
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
        \"$MARVEL\",
        \"$WBBROS\",
        \"$SONY_PICTURES\",
        \"$KINOCHECK\",
        \"$SONYLIV\",
        \"$ZEE_MUSIC\",
        \"$AHA\",
        \"$SONY_SOUTH\"
    ]
}"
